package de.bwaldvogel.mongo.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import de.bwaldvogel.mongo.bson.Document;

class CursorTest {

    @Test
    void documentsCount() {
        Cursor cursor = new Cursor("testcol");
        assertThat(cursor.documentsCount()).isEqualTo(0);
        cursor = new Cursor(Collections.singleton(new Document()), "testcol");
        assertThat(cursor.documentsCount()).isEqualTo(1);
    }

    @Test
    void isEmpty() {
        Cursor cursor = new Cursor("testcol");
        assertThat(cursor.isEmpty()).isTrue();
        cursor = new Cursor(Collections.singleton(new Document()), "testcol");
        assertThat(cursor.isEmpty()).isFalse();
    }

    @Test
    void getCursorId_emptyCursorShouldHaveIdEqualsToZero() {
        Cursor cursor = new Cursor("testcol");
        assertThat(cursor.getCursorId()).isEqualTo(0);
    }

    @Test
    void getCursorId_ShouldHaveIdEqualsGreaterThanZero() {
        Cursor cursor = new Cursor(Collections.singleton(new Document()), "testcol");
        assertThat(cursor.getCursorId()).isGreaterThan(0);
    }

    @Test
    void getDocuments() {
        Collection<Document> docs = Arrays.asList(new Document("name", "Joe"), new Document("name", "Mary"));
        Cursor cursor = new Cursor(docs, "testcol");
        for (Document doc : docs) {
            assertThat(cursor.getDocuments().poll()).isEqualTo(doc);
        }
    }

    @Test
    void getCollectionName() {
        Cursor cursor = new Cursor("testcol");
        assertThat(cursor.getCollectionName()).isEqualTo("testcol");
    }
}
