package com.project.stripeprototype;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.Stripe;
import com.stripe.android.model.Token;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    TextView Number;
    TextView Month;
    TextView Year;
    TextView CVC;
    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
        //Handler mErrorDialogHandler = Dialog()


        Number = (TextView) findViewById(R.id.cardNumber);
        Month = (TextView) findViewById(R.id.month);
        Year = (TextView) findViewById(R.id.year);
        CVC = (TextView) findViewById(R.id.cvc);

        text = (TextView) findViewById(R.id.text);

    }
    public void testFunction() {
        //Card cardToSave = mCardInputWidget.getCard();
//        if (cardToSave == null) {
//            //mErrorDialogHandler.showError("Invalid Card Data");
//        }


    }

    public void submitCard(View Bundle){
        Toast toast = Toast.makeText(getApplicationContext(), "Submit Initiated", Toast.LENGTH_LONG);
        toast.show();


        String cardNumber = Number.getText().toString();
        //String cardExpMonth = Month.getText().toString();
        //String cardExpYear = Year.getText().toString();
        String cardCVC = CVC.getText().toString();

        Card card = new Card(
                cardNumber,
                2,
                20,
                cardCVC
        );

        card.validateNumber();
        card.validateCVC();
        toast = Toast.makeText(getApplicationContext(), "CardNumber = "+cardNumber, Toast.LENGTH_LONG);
        toast.show();

        Stripe stripe = new Stripe(getApplicationContext(), "pk_test_YqW2PVujPB8FZa1nm7pckDAT");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server

                        Toast.makeText(getApplicationContext(),
                                "Token Sent To Server",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getApplicationContext(),
                                "Error",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
        text.setText(stripe.toString());
    }


}

