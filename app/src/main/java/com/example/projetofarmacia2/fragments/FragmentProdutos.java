package com.example.projetofarmacia2.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.adapters.MedicamentoListAdapter;
import com.example.projetofarmacia2.api_manipulacao.Busca_categorias;
import com.example.projetofarmacia2.api_manipulacao.Busca_medicametos;
import com.example.projetofarmacia2.api_manipulacao.Busca_todos_medicametos;

public class FragmentProdutos extends Fragment {

    View view;
    Spinner spinner;
    EditText buscar;
    MedicamentoListAdapter medicamentoListAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.produtos_fragment,container,false);
        spinner = (Spinner) view.findViewById(R.id.menu_categorias);
        buscar = (EditText) view.findViewById(R.id.editTextBuscar);






        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Busca_categorias busca_categorias = new Busca_categorias();
        busca_categorias.buscarCategorias(getContext(), getActivity(), getView());


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                //selecteditem =  adapter.getItemAtPosition(i).toString();
                //or this can be also right: selecteditem = level[i];
                /*Busca_produtos busca_produtos = new Busca_produtos();
                busca_produtos.buscarProdutos(getContext(),getActivity(),view,++i);*/



                if(i == 0){
                    Busca_todos_medicametos busca_todos_medicametos = new Busca_todos_medicametos();
                    busca_todos_medicametos.buscarTodosProdutos(getContext(),getActivity(),getView());

                }else if (i > 0){
                    Busca_medicametos busca_medicametos = new Busca_medicametos();
                    busca_medicametos.buscarProdutos(getContext(),getActivity(),getView(),i);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





    }




}
