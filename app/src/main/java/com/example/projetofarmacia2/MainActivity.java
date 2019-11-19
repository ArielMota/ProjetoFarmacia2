package com.example.projetofarmacia2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetofarmacia2.fragments.FragmentProdutos;
import com.example.projetofarmacia2.fragments.FragmentProdutosBaratos;

public class MainActivity extends FragmentActivity {

    Button bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.fragment_content) != null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_content, new FragmentProdutos()).commit();


            if (savedInstanceState != null) {
                return;
            }


        }




        /*bt = (Button) findViewById(R.id.btn);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_content, new FragmentProdutosBaratos());
                ft.commit();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


}
