package com.test.calculator_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button one, two, three, four, five, six, seven, eight, nine,
            zero, plus, minus, multiply, divide, clear, equal, percent, dot, back;
    EditText textOutput, textInput;
    String equation = "";
    String subEq = "";
    int index = 0;
    char operation = ' ';
    double value1 = 0;
    double value2 = 0;
    boolean isEqualActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUiViews();

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() == 0) {
                    equation += "0.";
                } else {
                    equation += ".";
                }
                textInput.setText(equation);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() > 0) {
                    try {
                        equation = equation.substring(0, equation.length() - 1);
                        textInput.setText(equation);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            }
        });


        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('0');

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('1');

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('2');

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('3');

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('4');

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('5');

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('6');

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('7');

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('8');

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic('9');

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() > 0 && checkForPreviousSymbols()) {


                    buttonLogic('+');
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForPreviousSymbols()) {
                    buttonLogic('-');

                }

            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() > 0 && checkForPreviousSymbols()) {
                    buttonLogic('x');

                }

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() > 0 && checkForPreviousSymbols()) {
                    buttonLogic('/');

                }

            }
        });
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() > 0 && checkForPreviousSymbols()) {
                    buttonLogic('%');
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initClear();
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (equation.length() > 0) {
                    isEqualActive = true;
                    System.out.println(equation);
                    performLogic();
                }
            }
        });
    }

    private void buttonLogic(char buttonText) {
        if (isEqualActive) {
            equation = String.valueOf(value1);
            value1 = Double.parseDouble(equation);
            index = equation.length() - 1;
            textInput.setText(equation);
            isEqualActive = false;
        }
        equation += buttonText;
        textInput.setText(equation);
    }

    private boolean checkForPreviousSymbols() {

        if (equation.length() == 0) {
            return true;
        }

        try {
            char symbol = (equation.charAt(equation.length() - 1));
            return symbol != '+' && symbol != '-' && symbol != 'x' && symbol != '/' && symbol != '%';
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private void initClear() {
        textInput.setText("");
        textOutput.setText("");
        equation = "";
        subEq = "";
        value1 = 0;
        value2 = 0;
        index = 0;
        operation = ' ';
        isEqualActive = false;
    }

    private void performLogic() {


        try {
            for (int i = 0; i < equation.length(); i++) {

                if (i == equation.length() - 1) {
                    subEq = equation.substring(index, (i + 1));
                    value2 = Double.parseDouble(subEq);
                    value1 = goForCalculation(value1, value2, operation);
                }

                if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == 'x' || equation.charAt(i) == '/'
                        || equation.charAt(i) == '%') {
                    subEq = equation.substring(index, i);
                    index = (i + 1);

                    if (value1 == 0) {
                        operation = equation.charAt(i);
                        value1 = Double.parseDouble(subEq);
                    } else {
                        value2 = Double.parseDouble(subEq);
                        value1 = goForCalculation(value1, value2, operation);
                        operation = equation.charAt(i);
                    }
                }
            }

            textOutput.setText(String.valueOf(value1));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double goForCalculation(double value1, double value2, char operation) {

        switch (operation) {
            case '+':
                return value1 + value2;

            case '-':
                return value1 - value2;

            case 'x':
                return value1 * value2;

            case '/':
                return value1 / value2;

            case '%':
                return value1 * (value2 / 100);

            default:
                System.out.print("default Case");
                return 0.0;
        }

    }

    private void setupUiViews() {
        back = findViewById(R.id.btnBack);
        zero = findViewById(R.id.button0);
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        four = findViewById(R.id.button4);
        five = findViewById(R.id.button5);
        six = findViewById(R.id.button6);
        seven = findViewById(R.id.button7);
        eight = findViewById(R.id.button8);
        nine = findViewById(R.id.button9);
        dot = findViewById(R.id.buttondot);
        percent = findViewById(R.id.buttonpercent);
        plus = findViewById(R.id.buttonplus);
        minus = findViewById(R.id.buttonminus);
        multiply = findViewById(R.id.buttoninto);
        divide = findViewById(R.id.buttondiv);
        clear = findViewById(R.id.buttonclear);
        equal = findViewById(R.id.buttonequal);
        textInput = findViewById(R.id.editText);
        textOutput = findViewById(R.id.textOutput);
    }
}