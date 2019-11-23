package com.example.projetofarmacia2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.adapters.ImagemPagerAdapter;
import com.example.projetofarmacia2.adapters.MedicamentoListAdapter;
import com.example.projetofarmacia2.api_manipulacao.APIconfig;
import com.example.projetofarmacia2.api_manipulacao.Busca_categorias;
import com.example.projetofarmacia2.api_manipulacao.Busca_medicametos;
import com.example.projetofarmacia2.api_manipulacao.Busca_todos_medicametos;
import com.example.projetofarmacia2.model.Medicamento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentMedicamentosDetalhes extends Fragment {

    View view;
    ViewPager viewPager;
    TextView nome, principioAtivo, concentracao, formaFarmaceutica, registroAnvisa, detentorRegistro, preco;
    ImageView voltar;
    Button localizar,farmacia;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.medicamentos_detalhes_fragment,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.img_medicamento_detalhes);
        nome = (TextView) view.findViewById(R.id.name_id);
        preco = (TextView) view.findViewById(R.id.preco_id);
        principioAtivo = (TextView) view.findViewById(R.id.pricipio_ativo);
        concentracao = (TextView) view.findViewById(R.id.concentracao);
        formaFarmaceutica = (TextView) view.findViewById(R.id.forma_farmaceutica);
        registroAnvisa = (TextView) view.findViewById(R.id.registro_anvisa);
        detentorRegistro = (TextView) view.findViewById(R.id.detentor_registro);

        localizar = (Button) view.findViewById(R.id.btn_localizar);
        farmacia = (Button) view.findViewById(R.id.btn_farmacia);

        voltar = (ImageView) view.findViewById(R.id.img_voltar);





        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> idImagem = null;
        Medicamento medicamento = new Medicamento();

        Bundle mBundle = new Bundle();
        if(mBundle != null){
            mBundle = getArguments();
            idImagem = mBundle.getStringArrayList("idImagem");
            medicamento = (Medicamento) mBundle.getSerializable("medicamento");


            ImagemPagerAdapter imagemPagerAdapter = new ImagemPagerAdapter(getContext(),idImagem);
            viewPager.setAdapter(imagemPagerAdapter);


        }

        nome.setText(medicamento.getNome());
        preco.setText("$ "+ medicamento.getPreco());
        principioAtivo.setText(medicamento.getPrincipioAtivo());
        detentorRegistro.setText(medicamento.getDetentorRegistro());
        concentracao.setText(medicamento.getConcentracao());
        formaFarmaceutica.setText(medicamento.getFormaFarmaceutica());
        registroAnvisa.setText(String.valueOf(medicamento.getRegistroAnvisa()));
        farmacia.setText(medicamento.getFarmacia().getNome());





        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                } else {
                    getActivity().onBackPressed();
                }
            }
        });


        final Medicamento finalMedicamento = medicamento;
        localizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Aqui  "+ finalMedicamento.getFarmacia().getEndereco().getLatitude());


                double  latitude = Double.valueOf(finalMedicamento.getFarmacia().getEndereco().getLatitude());
                double  longitude = Double.valueOf(finalMedicamento.getFarmacia().getEndereco().getLongitude());

                String strUri = "http://maps.google.com/maps?q=loc:" +
                        latitude + "," + longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");


                getContext().startActivity(intent);


            }
        });
    }




}
