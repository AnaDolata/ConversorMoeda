package com.example.conversormoeda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conversormoeda.Model.Coin;
import com.example.conversormoeda.apiCoin.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view){
        RadioButton deReal = findViewById(R.id.radioButtonDeReal);
        RadioButton deDolar = findViewById(R.id.radioButtonDeDolar);
        RadioButton deEuro = findViewById(R.id.radioButtonDeEuro);
        RadioButton deBitcoin = findViewById(R.id.radioButtonDeBitcoin);
        RadioButton deETH = findViewById(R.id.radioButtonDeEthereum);

        RadioButton paraReal = findViewById(R.id.radioButtonParaReal);
        RadioButton paraDolar = findViewById(R.id.radioButtonParaDolar);
        RadioButton paraEuro = findViewById(R.id.radioButtonParaEuro);
        RadioButton paraBitcoin = findViewById(R.id.radioButtonParaBitcoin);
        RadioButton paraETH = findViewById(R.id.radioButtonParaEthereum);

        EditText input = findViewById(R.id.editTextInput);
        TextView output = findViewById(R.id.textViewOutput);

        if(input.length() == 0){
            Toast.makeText(this, "Digite um número para converter!", Toast.LENGTH_SHORT).show();
        }else{
            double value = Double.parseDouble(input.getText().toString());
            String convert = null;

            if (deReal.isChecked() && paraReal.isChecked())
                convert = "BRL-BRL";
            if (deReal.isChecked() && paraDolar.isChecked())
                convert = "BRL-USD";
            if (deReal.isChecked() && paraBitcoin.isChecked())
                convert = "BRL-BTC";
            if (deReal.isChecked() && paraETH.isChecked())
                convert = "BRL-ETH";
            if (deReal.isChecked() && paraEuro.isChecked())
                convert = "BRL-EUR";

            if (deDolar.isChecked() && paraReal.isChecked())
                convert = "USD-BRL";
            if (deDolar.isChecked() && paraEuro.isChecked())
                convert = "USD-EUR";
            if (deDolar.isChecked() && paraDolar.isChecked())
                convert = "USD-USD";
            if (deDolar.isChecked() && paraBitcoin.isChecked())
                convert = "USD-BTC";
            if (deDolar.isChecked() && paraETH.isChecked())
                convert = "USD-ETH";

            if (deEuro.isChecked() && paraReal.isChecked())
                convert = "EUR-BRL";
            if (deEuro.isChecked() && paraDolar.isChecked())
                convert = "EUR-USD";
            if (deEuro.isChecked() && paraEuro.isChecked())
                convert = "EUR-EUR";
            if (deEuro.isChecked() && paraBitcoin.isChecked())
                convert = "EUR-BTC";
            if (deEuro.isChecked() && paraETH.isChecked())
                convert = "EUR-ETH";

            if (deETH.isChecked() && paraETH.isChecked())
                convert = "ETH-ETH";
            if (deETH.isChecked() && paraBitcoin.isChecked())
                convert = "ETH-BTC";
            if (deETH.isChecked() && paraReal.isChecked())
                convert = "ETH-BRL";
            if (deETH.isChecked() && paraEuro.isChecked())
                convert = "ETH-EUR";
            if (deETH.isChecked() && paraDolar.isChecked())
                convert = "ETH-USD";

            if (deBitcoin.isChecked() && paraETH.isChecked())
                convert = "BTC-ETH";
            if (deBitcoin.isChecked() && paraBitcoin.isChecked())
                convert = "BTC-BTC";
            if (deBitcoin.isChecked() && paraReal.isChecked())
                convert = "BTC-BRL";
            if (deBitcoin.isChecked() && paraDolar.isChecked())
                convert = "BTC-USD";
            if (deBitcoin.isChecked() && paraEuro.isChecked())
                convert = "BTC-EUR";

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Calculando...");
            progressDialog.show();

            Call<Coin> call = new RetrofitConfig().getCoinService().getCoin(convert);
            call.enqueue(new Callback<Coin>() {
                @Override
                public void onResponse(Call<Coin> call, Response<Coin> response) {
                    Coin coin = response.body();
                    progressDialog.dismiss();
                    Double high = coin.getHigh();
                    Double value = Double.parseDouble(input.toString());
                    Double result = high*value;
                    output.setText(result.toString());
                }

                @Override
                public void onFailure(Call<Coin> call, Throwable t) {

                }
            });
        }
    }
}