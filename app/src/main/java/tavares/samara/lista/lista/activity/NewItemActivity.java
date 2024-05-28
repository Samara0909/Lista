package tavares.samara.lista.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import tavares.samara.lista.R;
import tavares.samara.lista.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1; // constante para identificar a requisição de seleção de foto


    // Uri é um endereço para um dado que não está localizado dentro do espaço reservado a app
    // dessa forma, photoSelected guardará o endereço da foto selecionada pelo usuário, e não a foto em si.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // extensão de bordas
        setContentView(R.layout.activity_new_item); // define o layout da atividade

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // ajusta o padding para incluir as barras do sistema
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NewItemActivityViewModel vm = new ViewModelProvider( this ).get(
                NewItemActivityViewModel.class );
        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        if(selectPhotoLocation != null) {
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }

        ImageButton imgCI = findViewById(R.id.imbCl); // botão para escolher a imagem
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dentro do metodo onClick, executamos a abertura da galeria para escolha da foto
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // intent para abrir o documento
                photoPickerIntent.setType("image/*"); // define o tipo como imagem
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); // executa o Intent através do uso do método startActivityForResult
            }
        });

        Button btnAddItem = findViewById(R.id.bntAddItem); // adiciona o item
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri selectPhotoLocation = vm.getSelectPhotoLocation();
                if(selectPhotoLocation == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent(); // retorma o resultado
                i.setData(selectPhotoLocation); // define a URI da foto selecionada
                i.putExtra("title", title); // adiciona o título ao Intent
                i.putExtra("description", description); // adiciona a descrição ao Intent
                setResult(Activity.RESULT_OK, i); // fefine o resultado como OK
                finish(); // finaliza a atividade
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data); // onActivityResult entrega 3 parâmetros: data, resultCode e requestCode.
        if (requestCode == PHOTO_PICKER_REQUEST) { // confere se a requisiçao é a de seleção de foto
            if (resultCode == Activity.RESULT_OK) { // analisa se o resultado é OK
                Uri photoSelected = data.getData(); // obtem o URI da imagem escolhida e guarda dentro do atributo de classe photoSelected
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview); // Obtém a referencia ao ImageView de pre-visualização da foto
                imvfotoPreview.setImageURI(photoSelected); // define a imagem selecionada no ImageView

                NewItemActivityViewModel vm = new ViewModelProvider( this
                ).get( NewItemActivityViewModel.class );
                vm.setSelectPhotoLocation(photoSelected);

            }
        }
    }
}
