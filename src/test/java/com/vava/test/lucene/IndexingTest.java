package com.vava.test.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.vava.lucene.TestUtil;

import junit.framework.TestCase;

/**
 * @author Steve
 * Created on 2020-08
 */
public class IndexingTest extends TestCase {
    protected String[] ids = {"1", "2"};
    protected String[] unindexed = {"Netherlands", "Italy"};
    protected String[] unstored = {"Amsterdam has lots of bridges", "Venice has lots of canals"};
    protected String[] text = {"Amsterdam", "Venice"};

    private Directory directory;

    protected void setUp() throws Exception {
        // 每次测试前运行
        directory = new RAMDirectory();

        // 创建IndexWriter对象
        IndexWriter writer = getWriter();

        // 添加文档
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new Field("id", ids[i], Store.YES, Index.NOT_ANALYZED));
            doc.add(new Field("country", unindexed[i], Store.YES, Index.NO));
            doc.add(new Field("contents", unstored[i], Store.NO, Index.ANALYZED));
            doc.add(new Field("city", text[i], Store.YES, Index.ANALYZED));
            writer.addDocument(doc);
        }
        writer.close();
    }

    private IndexWriter getWriter() throws IOException {
        // 创建IndexWriter对象
        return new IndexWriter(directory, new WhitespaceAnalyzer(), MaxFieldLength.UNLIMITED);
    }

    protected int getHitCount(String filedName, String searchString) throws IOException {
        // 创建新的IndexSearcher对象
        IndexSearcher searcher = new IndexSearcher(directory);
        // 建立简单的单term查询
        Term t = new Term(filedName, searchString);
        Query query = new TermQuery(t);
        // 获取命中数
        int hitCount = TestUtil.hitCount(searcher, query);
        searcher.close();
        return hitCount;
    }

    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        // 核对写入的文档数
        assertEquals(ids.length, writer.numDocs());
        writer.close();
    }

    public void testIndexReader() throws IOException {
        IndexReader reader = IndexReader.open(directory);
        // 核对读入的文档数
        assertEquals(ids.length, reader.maxDoc());
        assertEquals(ids.length, reader.numDocs());
        reader.clone();
    }

    public void testDeleteBeforeOptimize() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();
        // 1、测试用例显示hasDeletions()方法用于检查索引中是否包含被标记为已删除的文档
        assertTrue(writer.hasDeletions());
        // 2、代码显示了两个通常容易混淆的方法：maxDoc()方法和numDoc()方法。前者返回索引中被删除和未被删除的文档总数，而后者只返回索引中未被删除的文档总数。
        // 本例中由于被索引包含两个文档其中一个已经被删除，所以numDocs()方法返回1而maxDoc()方法返回2
        assertEquals(2, writer.maxDoc());
        assertEquals(1, writer.numDocs());
        writer.close();
    }

    public void testDeleteAfterOptimize() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        // 3、在方法testDeleteAfterOptimize()中，我们调用索引优化来强制Lucene在删除一个文档后合并索引段。所以随后maxDoc()方法返回1而不是2，因为在删除和优化
        // 操作完成后，Lucene实际上已经将该文档删除，最后索引中只包含最后一个文档。
        writer.optimize();
        writer.commit();
        assertFalse(writer.hasDeletions());
        assertEquals(1, writer.maxDoc());
        assertEquals(1, writer.numDocs());
        writer.close();
    }

    public void testUpdate() throws IOException {
        assertEquals(1, getHitCount("city", "Amsterdam"));
        IndexWriter writer = getWriter();
        // 为"Haag"建立新文档
        Document doc = new Document();
        doc.add(new Field("id", "1", Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field("country", "Netherlands", Store.YES, Index.NO));
        doc.add(new Field("contents", "Den Haag has a lot of museums", Store.NO, Index.ANALYZED));
        doc.add(new Field("city", "Den Haag", Store.YES, Index.ANALYZED));
        // 更新文档版本
        writer.updateDocument(new Term("id", "1"), doc);
        writer.close();
        // 确认旧文档已删除
        assertEquals(0, getHitCount("city", "Amsterdam"));
        // 确认新文档已被索引
        assertEquals(1, getHitCount("city", "Den Haag"));
    }

    public void testBoost() throws IOException {
        Document doc = new Document();
        String senderEmail = getSenderEmail();
        String senderName = getSenderName();
        String subject = getSubject();
        String body = getBody();
        doc.add(new Field("senderEmail", senderEmail, Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field("senderName", senderName, Store.YES, Index.ANALYZED));
        doc.add(new Field("subject", subject, Store.YES, Index.ANALYZED));
        doc.add(new Field("body", body, Store.NO, Index.ANALYZED));
        String lowerDomain = getSendDomain().toLowerCase();
        if (isImportant(lowerDomain)) {
            doc.setBoost(1.5F);
        } else {
            doc.setBoost(0.1F);
        }
        getWriter().addDocument(doc);
    }

    private boolean isImportant(String lowerDomain) {
        return false;
    }

    private String getSendDomain() {
        return null;
    }

    private String getBody() {
        return null;
    }

    private String getSubject() {
        return null;
    }

    private String getSenderName() {
        return null;
    }

    private String getSenderEmail() {
        return null;
    }

}
