Feature: QRIS Payment Verification

  As a user
  I want to easily and securely complete payments using QRIS
  So that I can conduct transactions without any hassle.


  @TC_WA_075 @SuccessToTransferWithValidImageQRISFromLocal
  Scenario: [TC_WA_075] Verifikasi sukses melakukan pembayaran menggunakan QRIS dengan cara upload valid jpg
    Given user already login and open the QRIS payment page
    When user uploads a valid jpeg
    And user input nominal "1000"
    And user input catatan "TF lewat QR"
    And user click Lanjutkan and Konfirmasi Button
    And user input valid PIN "120951" and click Lanjutkan Button
    Then verify payment should be successful and display Message "Transaksi Berhasil"

  @TC_WA_076 @FailedToTransferBecauseInvalidImage
  Scenario: [TC_WA_076] Verifikasi gagal melakukan pembayaran menggunakan QRIS dengan cara upload invalid jpeg
    Given user already login and open the QRIS payment page
    When user uploads an invalid jpeg
    Then verify failed transfer and display message "Pemindaian QR Code Gagal, Format tidak valid! Silakan coba lagi."

  @TC_WA_077 @FailedToTransferBecauseNominal0_CaseUploadImageFromLocal
  Scenario: [TC_WA_077] Verifikasi gagal melakukan pembayaran menggunakan QRIS dengan cara upload jpeg karena nominal dibawah 1 rupiah
    Given user already login and open the QRIS payment page
    When user uploads a valid jpeg
    And user input nominal "0"
    And user input catatan "TF lewat QR"
    Then verify failed transfer with qris and display Message "Minimum transfer adalah 1, mohon isikan kembali"

  @TC_WA_078 @FailedToTransferBecauseMelebihiSaldo_CaseUploadImageFromLocal
  Scenario: [TC_WA_078] Verifikasi gagal melakukan pembayaran menggunakan QRIS dengan cara upload jpg dari local karena nominal melebihi saldo
    Given user already login and open the QRIS payment page
    When user uploads a valid jpeg
    And user input nominal "1000000000000000"
    Then verify failed transfer with qris and display Message "Saldo Tidak Cukup, mohon isikan kembali"

  @TC_WA_079 @FailedToTransferBecauseEmptyNominal_CaseUploadImageFromLocal
  Scenario: [TC_WA_079] Verifikasi gagal melakukan pembayaran menggunakan QRIS dengan cara upload jpg dari local karena tidak input nominal
    Given user already login and open the QRIS payment page
    When user uploads a valid jpeg
    And user input nominal ""
    And user input catatan "TF lewat QR"
    And user click Lanjutkan
    Then verify failed transfer with qris and display Message "Nominal Tidak Boleh Kosong"

  @TC_WA_080 @SuccessToTransferWithUploadImageDisableNominal
  Scenario: [TC_WA_080] Verifikasi sukses melakukan pembayaran menggunakan QRIS dengan cara upload jpeg yang nominalnya disable atau sudah diatur
    Given user already login and open the QRIS payment page
    When user upload jpg code qr disable nomnial
    And user input catatan "TF lewat QR"
    And user click Lanjutkan and Konfirmasi Button
    And user input valid PIN "120951" and click Lanjutkan Button
    Then verify payment should be successful and display Message "Transaksi Berhasil"
