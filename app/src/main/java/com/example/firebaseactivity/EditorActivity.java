package com.example.firebaseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditorActivity extends AppCompatActivity {

    private EditText editName,editEmail,editPosisi,editGaji,editSyarat;
    private Button btnSave;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private ProgressDialog proggressDialog;
    private String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editName=findViewById(R.id.namePeru);
        editEmail=findViewById(R.id.email);
        editPosisi=findViewById(R.id.posisi);
        editGaji=findViewById(R.id.gaji);
        editSyarat=findViewById(R.id.syarat);
        btnSave=findViewById(R.id.btn_save);

        proggressDialog=new ProgressDialog(EditorActivity.this);
        proggressDialog.setTitle("Loading");
        proggressDialog.setMessage("Menyimpan...");
        btnSave.setOnClickListener(v->{
            if(editName.getText().length()>0 &&editEmail.getText().length()>0
            &&editPosisi.getText().length()>0 &&editGaji.getText().length()>0&&editSyarat.getText().length()>0){
                saveData(editName.getText().toString(),editEmail.getText().toString(),editPosisi.getText().toString(),
                        editGaji.getText().toString(),editSyarat.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
            }
        } );
        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra("id");
            editName.setText(intent.getStringExtra("name"));
            editEmail.setText(intent.getStringExtra("email"));
            editPosisi.setText(intent.getStringExtra("posisi"));
            editGaji.setText(intent.getStringExtra("gaji"));
            editSyarat.setText(intent.getStringExtra("syarat"));
        }
    }
    private void saveData(String name, String email, String posisi, String gaji, String syarat){
        Map<String,Object>user=new HashMap<>();
        user.put("name",name);
        user.put("email",email);
        user.put("posisi",posisi);
        user.put("gaji",gaji);
        user.put("syarat",syarat);

        proggressDialog.show();
        if(id!=null){
            db.collection("users").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                                finish();
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"Gagal!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else
        {
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            proggressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            proggressDialog.dismiss();
                        }
                    });

        }
    }
}