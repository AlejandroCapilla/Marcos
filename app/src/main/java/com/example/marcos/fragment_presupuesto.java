package com.example.marcos;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class fragment_presupuesto extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, TextWatcher {

    private TextView txtAncho, txtAlto, txtOtroModelo, txtOtroColor, txtOtroColorModelo, txtAnchoMarialuisa, txtColorMarialuisa;
    private Spinner spnModelo, spnColor;
    private RadioGroup gpoVidrio;
    private RadioButton rdoAnti, rdoDobleAnti, rdoSinAnti;
    private CheckBox chkMdf, chkMarialuisa, chkBastidor, chkLaminado, chkPegEng;
    private TextView lblPrecio, lblMarialuisa;
    private ModelosSpinnerAdapter modelosSpinnerAdapter;

    private InputMethodManager imm;
    private TextView lblMensaje;

    public fragment_presupuesto() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presupuesto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CrearUI(view);
        lblMensaje = view.findViewById(R.id.lblMensaje);

        txtAncho.addTextChangedListener(this);
        txtAlto.addTextChangedListener(this);

        spnModelo.setOnItemSelectedListener(this);
        txtOtroModelo.addTextChangedListener(this);

        spnColor.setOnItemSelectedListener(this);
        txtOtroColorModelo.addTextChangedListener(this);

        chkMdf.setOnClickListener(this);
        chkMarialuisa.setOnClickListener(this);
        chkBastidor.setOnClickListener(this);
        chkLaminado.setOnClickListener(this);
        chkPegEng.setOnClickListener(this);

        txtAnchoMarialuisa.addTextChangedListener(this);

    }

    private void CrearUI(@NonNull View view) {
        txtAncho = view.findViewById(R.id.txtAncho);
        txtAlto = view.findViewById(R.id.txtAlto);
        txtOtroModelo = view.findViewById(R.id.txtOtroModelo);
        txtOtroColor = view.findViewById(R.id.txtOtroColor);
        txtOtroColorModelo = view.findViewById(R.id.txtOtroColorModelo);
        txtAnchoMarialuisa = view.findViewById(R.id.txtAnchoMarialuisa);
        txtColorMarialuisa = view.findViewById(R.id.txtColorMarialuisa);
        spnModelo = view.findViewById(R.id.spnModelo);
        spnColor = view.findViewById(R.id.spnColor);
        gpoVidrio = view.findViewById(R.id.gpoVidrio);
        rdoAnti = view.findViewById(R.id.rdoAnti);
        rdoDobleAnti = view.findViewById(R.id.rdoDobleAnti);
        rdoSinAnti = view.findViewById(R.id.rdoSinAnti);
        chkMdf = view.findViewById(R.id.chkMdf);
        chkMarialuisa = view.findViewById(R.id.chkMarialuisa);
        chkBastidor = view.findViewById(R.id.chkBastidor);
        chkLaminado = view.findViewById(R.id.chkLaminado);
        chkPegEng = view.findViewById(R.id.chkPegEng);
        lblPrecio = view.findViewById(R.id.lblPrecio);
        lblMarialuisa = view.findViewById(R.id.lblMarialuisa);

        //inicializa los elementos del spinnerModelos y los agrega
        Modelos.inicializarModelos();
        modelosSpinnerAdapter = new ModelosSpinnerAdapter(getContext(), R.layout.adapter_spinner_modelo, Modelos.getModelosArrayList());
        spnModelo.setAdapter(modelosSpinnerAdapter);

        spnColor.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, llenarSpnColor()));

        imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private boolean verificarDatos() {
        boolean completos = true;
        try {
            if (txtAncho.getText().toString().equals("")) {
                completos = false;
            } else if (txtAlto.getText().toString().equals("")) {
                completos = false;
            } else if (spnModelo.getSelectedItemPosition() == 7) {
                if (txtOtroModelo.getText().toString().equals("")) {
                    completos = false;
                }
            } else if (spnColor.getSelectedItemPosition() == 8) {
                if (txtOtroColorModelo.getText().toString().equals("")) {
                    completos = false;
                }
            }else if (chkMarialuisa.isChecked()) {
                if (txtAnchoMarialuisa.getText().toString().equals("")) {
                    completos = false;
                }
            }

            return completos;

        }catch(Exception e) {
            return false;
        }
    }

    private void calcularPrecio() {
        if(verificarDatos()) {//llenar objeto de la clase Producto

        }else {
            lblPrecio.setText("0.0");
        }
    }

    private void cerrarTeclado(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    String[] llenarSpnColor() {
        String[] colores = {
                "Negro (m:+0)",
                "Vino (m:+0)",
                "Nogal (m:+0)",
                "Chocolate (m:+0)",
                "Roble (m:+0)",
                "Natural (m:+0)",
                "Patinado dorado (m:+100)",
                "Patinado plata (m:+100)",
                "Otro (especificar color y m)"};
        return colores;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.spnModelo:
                if (i == 7) {
                    txtOtroModelo.setVisibility(View.VISIBLE);
                    txtOtroModelo.requestFocus();
                    imm.showSoftInput(txtOtroModelo,InputMethodManager.SHOW_IMPLICIT);
                } else {
                    txtOtroModelo.setVisibility(View.INVISIBLE);
                }

                if (i == 6) {
                    chkBastidor.setChecked(false);
                    chkBastidor.setVisibility(View.GONE);
                } else {
                    chkBastidor.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.spnColor:
                if (i == 8) {
                    txtOtroColor.setVisibility(View.VISIBLE);
                    txtOtroColor.requestFocus();
                    txtOtroColorModelo.setVisibility(View.VISIBLE);
                    imm.showSoftInput(txtOtroColor,InputMethodManager.SHOW_IMPLICIT);
                } else {
                    txtOtroColor.setVisibility(View.INVISIBLE);
                    txtOtroColorModelo.setVisibility(View.INVISIBLE);
                }
                break;
        }

        calcularPrecio();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.chkMarialuisa:
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
                break;
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
}

class ModelosSpinnerAdapter extends ArrayAdapter<Modelos> {

    LayoutInflater layoutInflater;

    public ModelosSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Modelos> modelos) {
        super(context, resource, modelos);

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.adapter_spinner_modelo, null, true);
        Modelos modelos = getItem(position);
        TextView textView = rowView.findViewById(R.id.txtSpinnerModelo);
        ImageView imageView = rowView.findViewById(R.id.icSpinerModelo);
        textView.setText(modelos.getNombre());
        imageView.setImageResource(modelos.getIcono());

        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_spinner_modelo, parent, false);
        }
        Modelos modelos = getItem(position);
        TextView textView = convertView.findViewById(R.id.txtSpinnerModelo);
        ImageView imageView = convertView.findViewById(R.id.icSpinerModelo);
        textView.setText(modelos.getNombre());
        imageView.setImageResource(modelos.getIcono());

        return convertView;
    }
}

class Modelos {
    private static ArrayList<Modelos> modelosArrayList = new ArrayList<>();
    private String nombre;
    private int icono;

    public Modelos(String nombre, int icono) {
        super();
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getIcono()
    {
        return icono;
    }

    public void setIcono(int icono)
    {
        this.icono = icono;
    }

    public static void inicializarModelos() {
        modelosArrayList.add(new Modelos("Media caña (m:150)",R.drawable.ic_media_cana));
        modelosArrayList.add(new Modelos("Media caña cuadrada (m:150)",R.drawable.ic_media_cana_cuadrada));
        modelosArrayList.add(new Modelos("3.5 lisa (m:180)",R.drawable.ic_35_lisa));
        modelosArrayList.add(new Modelos("3.5 (m:180)",R.drawable.ic_35));
        modelosArrayList.add(new Modelos("5.5 (m:240)", R.drawable.ic_55));
        modelosArrayList.add(new Modelos("5.5 + ML 3.5 (m:500)", R.drawable.ic_55_ml35));
        modelosArrayList.add(new Modelos("Bastidor (m:80)",R.drawable.ic_media_cana_bastidor));
        modelosArrayList.add(new Modelos("Otro (especificar m)",R.drawable.ic_baseline_arrow_forward_24));
        modelosArrayList.add(new Modelos("Ninguno (m:0)", R.drawable.ic_baseline_not_interested_24));
    }

    public static ArrayList<Modelos> getModelosArrayList() {
        return modelosArrayList;
    }
}