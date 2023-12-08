package com.example.gymforge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Desayuno extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DietasAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desayuno);

        recyclerView = findViewById(R.id.recyclerViewDesayuno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DietasAdapter();
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        String tipoComida = getIntent().getStringExtra("tipoComida");

        try {
            db.collection("Dietas")
                    .whereEqualTo("tipoComida", tipoComida)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        List<Dieta> dietas = new ArrayList<>();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Dieta dieta = document.toObject(Dieta.class);
                            dietas.add(dieta);
                        }
                        adapter.setDietas(dietas);
                    })
                    .addOnFailureListener(e -> {
                        // Manejar errores al obtener las dietas
                        Toast.makeText(getApplicationContext(), "Error al obtener las dietas", Toast.LENGTH_SHORT).show();
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}