package com.example.madassignment1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddEditBookActivity extends AppCompatActivity {

    private EditText etTitle, etAuthor, etIsbn, etYear;
    private Button btnSave;

    private FirebaseFirestore db;
    private String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);

        db = FirebaseFirestore.getInstance();

        etTitle = findViewById(R.id.et_title);
        etAuthor = findViewById(R.id.et_author);
        etIsbn = findViewById(R.id.et_isbn);
        etYear = findViewById(R.id.et_year);
        btnSave = findViewById(R.id.btn_save);

        bookId = getIntent().getStringExtra("book_id");

        if (bookId != null) {
            etTitle.setText(getIntent().getStringExtra("title"));
            etAuthor.setText(getIntent().getStringExtra("author"));
            etIsbn.setText(getIntent().getStringExtra("isbn"));
            etYear.setText(getIntent().getStringExtra("year"));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBook();
            }
        });
    }

    private void saveBook() {
        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String isbn = etIsbn.getText().toString();
        String year = etYear.getText().toString();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(isbn) || TextUtils.isEmpty(year)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> book = new HashMap<>();
        book.put("title", title);
        book.put("author", author);
        book.put("isbn", isbn);
        book.put("year", year);

        if (bookId != null) {
            db.collection("books").document(bookId)
                    .set(book)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AddEditBookActivity.this, "Book updated", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEditBookActivity.this, "Error updating book", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            db.collection("books")
                    .add(book)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddEditBookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEditBookActivity.this, "Error adding book", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}