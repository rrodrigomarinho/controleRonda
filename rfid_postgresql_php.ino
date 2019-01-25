#include <SPI.h>
#include <Ethernet.h>
#include <MFRC522.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = { 192, 168, 0, 104 };
byte servidor[] = { 192, 168, 0, 100 };

EthernetClient cliente;

#define SS_PIN 8
#define RST_PIN 9
MFRC522 mfrc522(SS_PIN, RST_PIN);

void setup() {
  Serial.begin(9600);
  SPI.begin();
  mfrc522.PCD_Init();
  Ethernet.begin(mac, ip);
  Serial.println("Aproxime o seu cartao do leitor!");
}

void loop() {
  if (!mfrc522.PICC_IsNewCardPresent()) {
    return;
  }
  if (!mfrc522.PICC_ReadCardSerial()) {
    return;
  }

  String cracha;
  for (byte i = 0; i < mfrc522.uid.size; i++) {
    cracha.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  if (cliente.connect(servidor, 8095)) {
    Serial.println("Conectado com sucesso!");

    cliente.print("GET /rfid/salvar_dados.php?");
    cliente.print("cracha=");
    cliente.print(cracha);
    cliente.print("&ponto=");
    cliente.println(Ethernet.localIP());

    Serial.print("Cracha = ");
    Serial.println(cracha);
    Serial.print("Ponto = ");
    Serial.println(Ethernet.localIP());
    Serial.println("Salvo com sucesso!");

    cliente.stop();
  } else {
    Serial.println("Falha na conexao!");
    cliente.stop();
  }
  delay(500);
}
