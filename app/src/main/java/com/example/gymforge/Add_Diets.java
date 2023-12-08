package com.example.gymforge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Add_Diets extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    String storage_path = "dietas/*";

    Button btnsave, btncleane;
    ImageView profile;
    ImageButton addimg;
    EditText name, descripcion, comida;

    private static final int COD_SEL_STORAGE =200;
    private static final int COD_SEL_IMAGE =300;

    private Uri image_url;
    String photo = "photo";
    String nombre;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diets);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        profile = findViewById(R.id.imgprofile);
        addimg = findViewById(R.id.addphoto);
        name =findViewById(R.id.txtNombre);
        descripcion= findViewById(R.id.txtDescrp);
        btnsave = findViewById(R.id.save_btn);
        comida = findViewById(R.id.txtcomida);

        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namediet = name.getText().toString().trim();
                String descriptiondiet = descripcion.getText().toString().trim();
                String TipoComida = comida.getText().toString().trim();

                if (namediet.isEmpty() || descriptiondiet.isEmpty() || TipoComida.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT);
                }
                else {
                    post(TipoComida, namediet,descriptiondiet);
                }

            }
        });
    }

    private void uploadPhoto(){
        Intent i= new Intent(Intent.ACTION_PICK);
        i.setType("image/*");

        startActivityForResult(i,COD_SEL_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == COD_SEL_IMAGE){
            image_url = data.getData();
            subirPhoto(image_url);
        }
    }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void subirPhoto (Uri image_url){
        progressDialog.setMessage("Actualizando foto");
        progressDialog.show();
        String rute_storage_photo = storage_path + "" + photo + "" + mAuth.getUid() + "" + nombre;
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful());
                uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String download_uri = uri.toString();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("photo", download_uri);
                        firebaseFirestore.collection("Dietas").document(nombre).update(map);
                    }
                });
            }
        });
    }

    private void post(String TipoComida,String namediet, String descriptiondiet){
        Map<String, Object> map = new HashMap<>();
        map.put("tipoComida",TipoComida);
        map.put("nombre",namediet);
        map.put("descripcion",descriptiondiet);

        firebaseFirestore.collection("Dietas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente",Toast.LENGTH_SHORT).show();

                Intent in=new Intent(Add_Diets.this,Add_Diets.class);
                startActivity(in);

                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}