package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class RegistroDatos extends AppCompatActivity implements View.OnClickListener {


    Button btnReg;
    EditText fecha,fechaExpe;
    EditText nombre,apellido,cedula,direccion,correo,ciudad,departamento,celular,nombreA,telefonoA;
    String usuario,nombres, apellidos, cedulas, fechaNacimiento, fechaExpedicion, direcciones, correos,ciudades,departamentos,celulares,nombreAcudiente,telefonoAcudiente;
    int ano, mes, dia;
    Calendar miCalendario;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);
        referenciar();
        mostrarFecha();

    }
    private void referenciar() {

        fecha = findViewById(R.id.txtFechaNacimiento);
        fechaExpe = findViewById(R.id.txtFechaExpedicion);
        miCalendario = Calendar.getInstance();
        ano = miCalendario.get(Calendar.YEAR);
        mes = miCalendario.get(Calendar.MONTH)+1;
        dia = miCalendario.get(Calendar.DAY_OF_MONTH);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        cedula = findViewById(R.id.txtCedula);
        direccion = findViewById(R.id.txtDireccion);
        correo = findViewById(R.id.txtCorreo);
        ciudad = findViewById(R.id.txtCiudad);
        departamento = findViewById(R.id.txtDepartamento);
        celular = findViewById(R.id.txtTelefono);
        nombreA = findViewById(R.id.txtNombreA);
        telefonoA = findViewById(R.id.txtTelefonoA);
        btnReg = findViewById(R.id.btnGuardarUsuario);
        progressDialog = new ProgressDialog(this);
        btnReg.setOnClickListener(this);
    }

    private void mostrarFecha() {
        String fechaAux= dia + "/" + mes + "/" + ano;
        fecha.setText(fechaAux);
        fechaExpe.setText(fechaAux);
    }
    public void mostrarCalendario(View view) {
        Calendar miCalendario = new GregorianCalendar();//Calendar.getInstance();
        miCalendario.setTime(new Date());
        new DatePickerDialog(this, R.style.TemaCalendario, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ano = year;
                mes = monthOfYear + 1;
                dia = dayOfMonth;
                mostrarFecha();
            }
        }, miCalendario.get(Calendar.YEAR), miCalendario.get(Calendar.MONTH), miCalendario.get(Calendar.DAY_OF_MONTH)).show();
    }


    public void registrarDatos()

    {

        nombres = nombre.getText().toString();
        apellidos = apellido.getText().toString();
        cedulas = cedula.getText().toString();
        fechaNacimiento = fecha.getText().toString();
        fechaExpedicion = fechaExpe.getText().toString();
        direcciones = direccion.getText().toString();
        correos = correo.getText().toString();
        ciudades = ciudad.getText().toString();
        departamentos = departamento.getText().toString();
        celulares = celular.getText().toString();
        nombreAcudiente = nombreA.getText().toString();
        telefonoAcudiente = telefonoA.getText().toString();




                progressDialog.setMessage("Registrando Datos....");
                progressDialog.show();


                                    // si la tarea se relalizo
                                  Map<String,Object> map = new HashMap<>();
                                    map.put("nombre",nombres);
                                    map.put("apellidos",apellidos);
                                    map.put("cedula",cedulas);
                                    map.put("fechaNacimiento",fechaNacimiento);
                                    map.put("fechaExpedicion",fechaExpedicion);
                                    map.put("direccion",direcciones);
                                   map.put("correo",correos);
                                   map.put("ciudad",ciudades);
                                   map.put("departamento",departamentos);
                                   map.put("celular",celulares);
                                   map.put("nombreAcudiente",nombreAcudiente);
                                   map.put("telefonoAcudiente",telefonoAcudiente);


                                    db.collection("users")
                                            .add(map)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(RegistroDatos.this, "Datos registrados correctamente", Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(RegistroDatos.this,fragmento.class));
                                                    finish();
                                                }
                                            })

                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(RegistroDatos.this, "Error al registrar datos ", Toast.LENGTH_SHORT).show();
                                                }
                                            });



                                progressDialog.dismiss();



            }





  @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnGuardarUsuario) {

            registrarDatos();
        }
    }

}

