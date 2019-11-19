package com.example.projetofarmacia2.api_manipulacao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.model.Categoria;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Busca_categorias {

    String url ;

    ArrayAdapter<Categoria> adapter;
    Spinner spinner;



    public void buscarCategorias(final Context context, final Activity activity, final View view) {

        url = APIconfig.URL;
        String urllocal = "/categoria";


            RequestQueue requestQueue = Volley.newRequestQueue(context);


            StringRequest stringRequest = new StringRequest(Request.Method.GET, url+urllocal, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_RESPONSE", response);

                    spinner = (Spinner) view.findViewById(R.id.menu_categorias);
                    List<Categoria> listCategoria = new ArrayList<>();

                    String res_body = response;

                    Gson gson = new Gson();
                    Categoria[] cat_array = gson.fromJson(res_body, Categoria[].class);

                    Categoria categoria =new Categoria(0,"Todos Medicamentos");
                    listCategoria.add(0,categoria);

                    for (int i = 0; i < cat_array.length; i++) {

                            listCategoria.add(cat_array[i]);

                    }

                    adapter = new ArrayAdapter<Categoria>(context, android.R.layout.simple_spinner_item, listCategoria);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner.setAdapter(adapter);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());

                    Toast.makeText(context,
                            "Verifique os Dados e Tente Novamente!",
                            Toast.LENGTH_LONG).show();
                }
            });

            requestQueue.add(stringRequest);


    }

}
