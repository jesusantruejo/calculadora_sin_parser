package com.example.calculadorajesusaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Object> pila;
    ArrayList<Object> buffer;
    ArrayList<String> memoria;
    HashMap<String, ArrayList<Object>> historial;
    int index=0;
    String visual="";
    double currentResult;
    String currentOperator="";
    TextView resultado;
    TextView realTime;
    int howDeep=0;
    int deepLimit=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado=findViewById(R.id.textViewResultado);
        realTime=findViewById(R.id.textViewRealTime);
        pila=new ArrayList<Object>();
        memoria=new ArrayList<String>();
        pila.add("0");

        //NUMEROS
        Button button0=findViewById(R.id.button0);
        button0.setOnClickListener(this);
        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6=findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7=findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8=findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9=findViewById(R.id.button9);
        button9.setOnClickListener(this);
        //PARENTESIS
        Button buttonLeft=findViewById(R.id.buttonleftPar);
        buttonLeft.setOnClickListener(this);
        Button buttonRight=findViewById(R.id.buttonrightPar);
        buttonRight.setOnClickListener(this);
        //= y .
        Button buttonEquals=findViewById(R.id.buttonequals);
        buttonEquals.setOnClickListener(this);
        Button buttonDot=findViewById(R.id.buttondot);
        buttonDot.setOnClickListener(this);
        //OPERADORES
        Button buttonPlus=findViewById(R.id.buttonplus);
        buttonPlus.setOnClickListener(this);
        Button buttonMinus=findViewById(R.id.buttonminus);
        buttonMinus.setOnClickListener(this);
        Button buttonMultiply=findViewById(R.id.buttonmultiply);
        buttonMultiply.setOnClickListener(this);
        Button buttonDivide=findViewById(R.id.buttondivide);
        buttonDivide.setOnClickListener(this);
        //MEMORIA Y BORRAR
        Button buttonSqrt=findViewById(R.id.buttonRoot);
        buttonSqrt.setOnClickListener(this);
        Button buttonBorrador=findViewById(R.id.buttonBorrar);
        buttonBorrador.setOnClickListener(this);
        Button buttonDelete=findViewById(R.id.buttondel);
        buttonDelete.setOnClickListener(this);
        buffer=new ArrayList<Object>();
        historial=new HashMap<String, ArrayList<Object>>();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            if(resultCode!=1){
                pila.clear();
                pila= new ArrayList<Object>(data.getParcelableArrayListExtra("devolucion"));
                index=pila.size()-1;
                String operacionCompleta=data.getStringExtra("devolucionVisual");
                visual=operacionCompleta.substring(0,operacionCompleta.indexOf("="));
                currentResult=calculadora(deepest(pila));
                resultado.setText(""+visual);
                realTime.setText(""+currentResult);
            }
        }
    }

    @Override
    public void onClick(View v) {
        profundidad(buffer);
        currentResult=0;
        deepLimit=0;

        switch (v.getId()) {
            case R.id.buttonequals:

                ArrayList<Object>copiaHistorial=new ArrayList<Object>();
                for (Object cop:pila){
                    copiaHistorial.add(cop);
                }
                currentResult=calculadora(deepest(buffer));
                historial.put(visual+"="+currentResult,copiaHistorial);
                pila.clear();
                visual=currentResult+"";
                pila.add(""+currentResult);
                index=0;


                break;
            case R.id.button0:
                deepest(pila).set(index,deepest(pila).get(index)+"0");
                visual=visual+"0";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button1:
                deepest(pila).set(index,deepest(pila).get(index)+"1");
                visual=visual+"1";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button2:
                deepest(pila).set(index,deepest(pila).get(index)+"2");
                visual=visual+"2";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button3:
                deepest(pila).set(index,deepest(pila).get(index)+"3");
                visual=visual+"3";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button4:
                deepest(pila).set(index,deepest(pila).get(index)+"4");
                visual=visual+"4";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button5:
                deepest(pila).set(index,deepest(pila).get(index)+"5");
                visual=visual+"5";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button6:
                deepest(pila).set(index,deepest(pila).get(index)+"6");
                visual=visual+"6";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button7:
                deepest(pila).set(index,deepest(pila).get(index)+"7");
                visual=visual+"7";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button8:
                deepest(pila).set(index,deepest(pila).get(index)+"8");
                visual=visual+"8";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.button9:
                deepest(pila).set(index,deepest(pila).get(index)+"9");
                visual=visual+"9";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.buttondot:
                deepest(pila).set(index,deepest(pila).get(index)+".");
                visual=visual+".";
                operatorSetter(deepest(pila));
                currentOperator="";
                break;
            case R.id.buttonleftPar:
                operatorSetter(deepest(pila));
                ArrayList<Object> deeper=new ArrayList<Object>();
                memoria.add(deepest(pila).get(index).toString().substring(0,1));
                deeper.add("0");
                deepest(pila).set(index,deeper);
                currentOperator="";
                visual=visual+"(";
                index=0;
                break;
            case R.id.buttonrightPar:
                currentResult=calculadora(deepest(pila));
               String signo="";
               String input="";
                for(String s:memoria){
                   signo=s;
               }
                if(signo.equals("+")){
                    currentResult=currentResult*(+1);
                    input=""+currentResult;
                }
                if(signo.equals("-")){
                    currentResult=currentResult*(-1);
                    input=""+currentResult;
                }

                if(signo.equals("*")){
                    input="*0"+currentResult;
                }
                if(signo.equals("/")){
                    input="/0"+currentResult;
                }
                if(signo.equals("√")){
                    input="√0"+Integer.parseInt(currentResult+"");
                }
                memoria.remove(memoria.size()-1);
                index=deepestMenosUno(pila,null).size()-1;
                deepestMenosUno(pila,null).set(index,input);
                visual=visual+")";
                break;

            case R.id.buttonplus:

                if(currentOperator!=""){
                    visual=visual+"+";
                    String pls=""+deepest(pila).get(index);
                    deepest(pila).set(index,pls.charAt(0)+"+"+pls.substring(1));

                    break;
                }
                else{
                    visual=visual+"+";
                    if(currentOperator==""){
                        deepest(pila).add("+0");
                        index++;
                        break;
                    }
                    else{
                        deepest(pila).add("+0");
                        index++;
                        break;
                    }
                }

            case R.id.buttonminus:
                if(currentOperator!=""){
                    visual=visual+"-";
                    String pls=""+deepest(pila).get(index);
                    deepest(pila).set(index,"-"+pls);
                    break;
                }
                else{
                    visual=visual+"-";
                    if(currentOperator==""){
                        deepest(pila).add("-0");
                        index++;
                        break;
                    }
                    else{
                        deepest(pila).add("-0");
                        index++;
                        break;
                    }
                }
            case R.id.buttonmultiply:
                currentOperator="*";
                visual=visual+"*";
                index++;
                deepest(pila).add("0");
                break;

            case R.id.buttondivide:
                currentOperator="/";
                visual=visual+"/";
                index++;
                deepest(pila).add("0");
                break;

            case R.id.buttonRoot:
                currentOperator="√";
                visual=visual+"√";
                break;


            case R.id.buttondel:
                currentOperator="";
                currentResult=0;
                visual="";
                deepest(pila).clear();
                deepest(pila).add("0");
                index=0;
                break;

            case R.id.buttonBorrar:
                if(deepest(pila).isEmpty()!=true){
                    deepest(pila).remove(index);
                    currentOperator="";
                    index=pila.size()-1;
                }
                visual="";
                for (Object op:pila){
                    visual=visual+op.toString();
                }
                break;


            default:
                break;
        }


        buffer.clear();
        for(Object c:pila){
            buffer.add(c);
        }
        currentResult=calculadora(deepest(buffer));
        resultado.setText(""+visual);
        realTime.setText(""+currentResult);

    }



    public ArrayList<Object> deepest(ArrayList<Object> al){
        ArrayList<Object> input=al;
        for(Object o:input){
            if(o instanceof ArrayList){
                return deepest((ArrayList<Object>) o);
            }
        }
       return input;
    }


    public ArrayList<Object> deepestMenosUno(ArrayList<Object> al, ArrayList<Object> test){
        ArrayList<Object> input=al;
        for(Object o:input){
            if(o instanceof ArrayList){
            return deepestMenosUno((ArrayList<Object>) o, input);
            }
        }
        return test;
    }

    public void profundidad(ArrayList<Object> al){
       howDeep=0;
        ArrayList<Object> input=al;
        for(Object o:input){
            if(o instanceof ArrayList){
                howDeep++;
                deepest((ArrayList<Object>) o);
            }
        }
    }





    public Double calculadora(ArrayList<Object> a){
        double total=0;
        int indice=0;
        ArrayList<Object> copiaEnUso=new ArrayList<Object>(a);
        for(Object num:copiaEnUso){

                if (num.toString().substring(0,1).equals("*")){
                    copiaEnUso.set(indice-1, Double.toString(Double.parseDouble(copiaEnUso.get(indice-1).toString())*Double.parseDouble(copiaEnUso.get(indice).toString().substring(1)))   );
                    copiaEnUso.remove(indice);
                    ArrayList<Object> iteration=new ArrayList<Object>();
                    for (Object p:copiaEnUso){
                        iteration.add(p);
                    }
                   indice--;
                   return calculadora(iteration);
                }
                else if(num.toString().substring(0,1).equals("/")){
                    copiaEnUso.set(indice-1,  Double.toString( Double.parseDouble(copiaEnUso.get(indice-1).toString())/Double.parseDouble(copiaEnUso.get(indice).toString().substring(1))));
                    copiaEnUso.remove(indice);
                    ArrayList<Object> iteration=new ArrayList<Object>();
                    for (Object p:copiaEnUso){
                        iteration.add(p);
                    }
                    indice--;
                    return calculadora(iteration);

                }
                else if(num.toString().substring(0,1).equals("√")){
                    copiaEnUso.set(indice,  Double.toString(Math.sqrt(Integer.parseInt(copiaEnUso.get(indice).toString().substring(1)))));
                    ArrayList<Object> iteration=new ArrayList<Object>();
                    for (Object p:copiaEnUso){
                        iteration.add(p);
                    }
                    return calculadora(iteration);
                }
                else{
                    indice++;
                }
        }
        for(Object numn:copiaEnUso){
            total=total+Double.parseDouble(numn.toString());
        }
        return total;
    }

   public void operatorSetter(ArrayList<Object> a){
       if(currentOperator!=""){
           String change= (String) deepest(a).get(index);
           change=currentOperator+change;
           deepest(a).set(index,change);
       }
   }

    public void sendMessage(View view) {
        Intent intentHistory = new Intent(this, History.class);
        intentHistory.putExtra("historial",historial);
        startActivityForResult(intentHistory,2);
    }

}
