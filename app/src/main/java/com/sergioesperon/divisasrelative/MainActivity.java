package com.sergioesperon.divisasrelative;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Button btnAccion;
    EditText txtUsa;
    EditText txtMex;
    RadioGroup rg;
    RadioButton rbUsa;
    RadioButton rbMex;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccion=(Button)findViewById(R.id.btnAccion);
        txtMex=(EditText)findViewById(R.id.txtMex);
        txtUsa=(EditText)findViewById(R.id.txtUsa);
        rg=(RadioGroup)findViewById(R.id.rg);
        rbUsa=(RadioButton)findViewById(R.id.rbUsa);
        rbMex=(RadioButton)findViewById(R.id.rbMex);
        tvResult=(TextView)findViewById(R.id.tvResult);

        rbUsa.setChecked(true);

        btnAccion.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnAccion.getId())
        {
            if(rbUsa.isChecked())
                Convertir(txtUsa,true);
            else
                Convertir(txtMex,false);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        String mensaje = rbUsa.getId() == checkedId ? "Selecciono USA" : "Selecciono MEX";
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void Convertir(EditText text, boolean opcion)
    {
        if(text.getText().toString().isEmpty())
        {
            text.setError("No tenemos datos a convertir");
            text.requestFocus();
        }else{
            try
            {
                double valor= Double.parseDouble(text.getText().toString());
                Conversion con= new Conversion();
                NumberFormat numberFormat= NumberFormat.getCurrencyInstance();
                double res=opcion==true?con.dolarToPeso(valor):con.pesoToDolar(valor);
                tvResult.setText(numberFormat.format(res));

                if(opcion)

                    txtMex.setText(String.valueOf(res));
                else

                    
                    txtUsa.setText(String.valueOf(res));
                //txtMex.setText(""+pesos);
            }
            catch(NumberFormatException ex)
            {
                Toast.makeText(this,"Error especifico: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
