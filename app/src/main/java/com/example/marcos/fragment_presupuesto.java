package com.example.marcos;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class fragment_presupuesto extends Fragment implements View.OnClickListener, TextWatcher, RadioGroup.OnCheckedChangeListener {
    private TextInputEditText txtAncho, txtAlto, txtOtroModelo;
    private RadioGroup gpoVidrio;
    private RadioButton rdoAnti, rdoDobleAnti, rdoSinAnti, rdoResina, rdoPolioleo;
    private CheckBox chkLaminado, chkPegEng;
    private TextView lblPrecio;
    private TextView lblMensaje;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presupuesto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CrearUI(view);
        txtAncho.addTextChangedListener(this);
        txtAlto.addTextChangedListener(this);
        txtOtroModelo.addTextChangedListener(this);
        gpoVidrio.setOnCheckedChangeListener(this);
        chkLaminado.setOnClickListener(this);
        chkPegEng.setOnClickListener(this);

        txtOtroModelo.setOnEditorActionListener((textView, i, keyEvent) -> {
            boolean handled = false;
            if (i == EditorInfo.IME_ACTION_DONE) {
                handled = true;
                txtOtroModelo.clearFocus();
                InputMethodManager imn = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
                imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            return handled;
        });

    }

    private void CrearUI(@NonNull View view) {
        txtAncho = view.findViewById(R.id.txtAncho);
        txtAlto = view.findViewById(R.id.txtAlto);
        txtOtroModelo = view.findViewById(R.id.txtOtroModelo);

        gpoVidrio = view.findViewById(R.id.gpoVidrio);
        rdoAnti = view.findViewById(R.id.rdoAnti);
        rdoDobleAnti = view.findViewById(R.id.rdoDobleAnti);
        rdoSinAnti = view.findViewById(R.id.rdoSinAnti);
        rdoResina = view.findViewById(R.id.rdoResina);
        rdoPolioleo = view.findViewById(R.id.rdoPolioleo);

        chkLaminado = view.findViewById(R.id.chkLaminado);
        chkPegEng = view.findViewById(R.id.chkPegEng);
        lblPrecio = view.findViewById(R.id.lblPrecio);
    }

    private boolean verificarDatos() {
        boolean completos = true;
        try {
            if (txtAncho.getText().toString().equals("")) {
                completos = false;
            } else if (txtAlto.getText().toString().equals("")) {
                completos = false;
            }else if (txtOtroModelo.getText().toString().equals("")) {
                    completos = false;
            }

            return completos;

        }catch(Exception e) {
            return false;
        }
    }

    private void calcularPrecio() {
        if(verificarDatos()) {//llenar objeto de la clase Producto
            float ancho = Float.parseFloat(txtAncho.getText().toString());
            float alto = Float.parseFloat(txtAlto.getText().toString());
            int modelo = Integer.parseInt(txtOtroModelo.getText().toString());
            String vidrio = obtenerVidrio();
            Producto producto = new Producto(ancho, alto, modelo, vidrio, false, false);
            lblPrecio.setText("$"+producto.getPrecio());
        }else {
            lblPrecio.setText("0");
        }
    }

    private String obtenerVidrio() {
        String vidrio = "";
        switch (gpoVidrio.getCheckedRadioButtonId()) {
            case R.id.rdoAnti: vidrio = "Anti"; break;
            case R.id.rdoDobleAnti: vidrio = "Doble Anti"; break;
            case R.id.rdoSinAnti: vidrio = "Sin Anti"; break;
            case R.id.rdoResina: vidrio = "Resina"; break;
            case R.id.rdoPolioleo: vidrio = "Polioleo";
        }

        return vidrio;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            /*case R.id.chkMarialuisa:
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    lblMarialuisa.setVisibility(View.VISIBLE);
                    txtAnchoMarialuisa.setVisibility(View.VISIBLE);
                    txtColorMarialuisa.setVisibility(View.VISIBLE);
                    txtAnchoMarialuisa.requestFocus();
                    //Crear metodo que solo abra el teclado si este no esta abiertp
                    imm.showSoftInput(txtAnchoMarialuisa,InputMethodManager.SHOW_IMPLICIT);
                } else {
                    lblMarialuisa.setVisibility(View.GONE);
                    txtAnchoMarialuisa.setVisibility(View.GONE);
                    txtColorMarialuisa.setVisibility(View.GONE);
                    //Crear metodo que solo cierre el teclado si este no esta cerrado
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;*/
        }

        calcularPrecio();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        calcularPrecio();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        calcularPrecio();
    }
}