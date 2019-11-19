package com.example.projetofarmacia2.api_manipulacao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.adapters.MedicamentoBaratoListAdapter;
import com.example.projetofarmacia2.adapters.MedicamentoListAdapter;
import com.example.projetofarmacia2.model.Farmacia;
import com.example.projetofarmacia2.model.Imagem;
import com.example.projetofarmacia2.model.Medicamento;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Busca_medicametos_mais_baratos {

    String url ;



    //Busca medicamentos mais baratos pelo nome
    public void buscarProdutos(final Context context, final Activity activity, final View view, String nome) {

        url = APIconfig.URL;
        String urllocal = "/menor/produto/?nome=";


        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+urllocal+nome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);

                List<Medicamento> medicamentos = new ArrayList<Medicamento>();


                //Tranforma json em lista de objetos
                try {
                    JSONArray produtosJson = new JSONArray(response);
                    JSONObject produto;

                    JsonArray id_imagens;
                    List<Imagem> list_img = new ArrayList<>();

                    for (int i = 0; i < produtosJson.length(); i++) {
                        produto = new JSONObject(produtosJson.getString(i));
                        // Log.i("PESSOA ENCONTRADA: ", "id=" + produto.getString("id"));

                        Medicamento objetoMedicamento = new Medicamento();
                        objetoMedicamento.setId(produto.getLong("id"));
                        objetoMedicamento.setNome(produto.getString("nome"));
                        objetoMedicamento.setPrincipioAtivo(produto.getString("principioAtivo"));
                        objetoMedicamento.setConcentracao(produto.getString("concentracao"));
                        objetoMedicamento.setFormaFarmaceutica(produto.getString("formaFarmaceutica"));
                        objetoMedicamento.setRegistroAnvisa(produto.getInt("registroAnvisa"));
                        objetoMedicamento.setDetentorRegistro(produto.getString("detentorRegistro"));
                        objetoMedicamento.setPreco(Double.valueOf(produto.getString("preco")));
                        objetoMedicamento.setQnt(produto.getInt("quantidade"));


                        //Pegar dados da farmacia
                        JSONObject jsFarmacia = produto.getJSONObject("farmacia");
                        Farmacia farmacia = new Farmacia();
                        //objetoMedicamento.getFarmacia().setNome(jsFarmacia.getString("nome"));
                        farmacia.setNome(jsFarmacia.getString("nome"));
                        objetoMedicamento.setFarmacia(farmacia);



                        JSONArray js = produto.getJSONArray("imagens");


                        List<String> list = new ArrayList<String>();
                        for (int j=0; j<js.length(); j++) {
                            list.add(js.getJSONObject(j).getString("id"));
                        }

                        objetoMedicamento.setLista_id_imagens(list);


                        //System.out.println(objetoMedicamento.getLista_id_imagens().size());


                        /*if (produto.getString("sexo").equals(sexo)) {

                                medicamentos.add(objetoMedicamento);


                            }*/

                        medicamentos.add(objetoMedicamento);


                    }


                //Preenche o recyclerview com medicamentos
                RecyclerView myrecyclerview = (RecyclerView) view.findViewById(R.id.my_recycler_view2);
                MedicamentoBaratoListAdapter medicamentoBaratoListAdapter = new MedicamentoBaratoListAdapter(medicamentos,context,activity);
                myrecyclerview.setLayoutManager(new LinearLayoutManager(activity));
                //myrecyclerview.setLayoutManager(new GridLayoutManager(activity,2));

                myrecyclerview.setAdapter(medicamentoBaratoListAdapter);


                } catch (JSONException e) {
                    Log.e("Erro", "kkkkkkkkkkkkkkkkkkkkkkkkkkkkk", e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_RESPONSE", error.toString());

                Toast.makeText(context,
                        "Verifique a internet e Tente Novamente!",
                        Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);


    }

}
