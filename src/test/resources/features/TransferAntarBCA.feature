@TransferAntarBCA
Feature: Transfer Antar BCA

  As a user
  I want to transfer funds to another BCA account
  So that I can easily send money to other BCA users

  @TC_WA_010 @SuccessSavedAccountRekeningToSavedList
  Scenario: [TC_WA_010] Verify success saved Account Rekening To Saved List
    Given user already login and is on the menu Transfer Antar BCA
    When user click TransferKeTujuanBaru button
    And user input nomor transfer baru "1122334455" in the field
    And user click CariNomor button
    And user checklist the box to add to saved list
    And user choose SumberRekening
    And user input nominal transfer "1000" in the field
    And user input catatan "Transfer Kekayaan" in the field
    And user click the Lanjutkan button after finished input the data
    And user click the Transfer button
    And user input pin "120951" in the field
    And user click the Lanjutkan button
    And user click Kembali Ke Homepage Button
    And user click transfer Menu and select menu Transfer Antar BCA
    Then validate success to saved Account Rekening with account "1122334455"

  @TC_WA_011 @SuccessToFavoriteAccountRekening
  Scenario: [TC_WA_011] Verify success to favorite Account Rekening
    Given user already login and is on the menu Transfer Antar BCA
    When user click love from Saved List with account 1122334455
    Then validate success to favorite Account Rekening with account "1122334455"

  @TC_WA_012_sd_018 @SuccessOrFailedTransferToNewAccountDestination
  Scenario Outline: [TC_WA_012 s/d TC_WA_018] Verify success or failed transfer to new account destination
    Given user already login and is on the menu Transfer Antar BCA
    When user click TransferKeTujuanBaru button
    And user input nomor transfer baru "<NomorTransferBaru>" in the field
    And user click CariNomor button
    And user choose SumberRekening
    And user input nominal transfer "<NominalTransfer>" in the field
    And user input catatan "<Catatan>" in the field
    And user click the Lanjutkan button after finished input the data
    And user click the Transfer button
    And user input pin "<PIN>" in the field
    And user click the Lanjutkan button
    Then validate the transfer is successful or failed and display the message "<expectedResult>"

    Examples:
      | ScenarioTitle                                   | NomorTransferBaru | NominalTransfer | Catatan           | PIN     | expectedResult                                  |
      | TC_WA_012 @Case_Positive1_ValidData             | 3344556677        | 1000            | Transfer Kekayaan | 120951  | Transaksi Berhasil                              |
      | TC_WA_013 @Case_Negative1_InvalidNoTujuan       | 111111245566      |                 |                   |         | NOMOR TIDAK TERDAFTAR                           |
      | TC_WA_014 @Case_Negative2_EmptyNominalTransfer  | 3344556677        |                 | Transfer Kekayaan |         | Nominal Tidak Boleh Kosong                      |
      | TC_WA_015 @Case_Negative3_NominalTransfer<1     | 3344556677        | 0               |                   |         | Minimum transfer adalah 1, mohon isikan kembali |
      | TC_WA_016 @Case_Negative4_NominalTransfer>Saldo | 3344556677        | 99993399800000  |                   |         | Saldo Tidak Cukup, mohon isikan kembali         |
      | TC_WA_017 @Case_Positive2_EmptyCatatan          | 3344556677        | 1000            |                   | 120951  | Transaksi Berhasil                              |
      | TC_WA_018 @Case_Negative5_InvalidPIN            | 3344556677        | 1000            | Transfer Kekayaan | 000000  | PIN Anda Salah                                  |

  @TC_WA_019_sd_024 @SuccessOrFailedTransferByChoosingFromSavedList
  Scenario Outline: [TC_WA_019 sd TC_WA_024] Verify success or failed Transfer by choosing from the Saved List
    Given user already login and is on the menu Transfer Antar BCA
    When user chooses from a saved list to transfer money
    And user input nominal transfer "<NominalTransfer>" in the field
    And user input catatan "<Catatan>" in the field
    And user click Lanjutkan button after finished input the data
    And user click the Transfer button
    And user input pin "<PIN>" in the field
    And user click the Lanjutkan button
    Then validate the transfer is successful or failed and display the message "<expectedResult>"

    Examples:
      | ScenarioTitle                                                                                 | NominalTransfer | Catatan           | PIN     |  expectedResult                                  |
      | TC_WA_019 @Case_Positive3_ValidData(memilih nomor rekening dari daftar tersimpan)             | 1000            | Transfer Kekayaan | 120951  |  Transaksi Berhasil                              |
      | TC_WA_020 @Case_Negative6_NominalTransfer<1(memilih nomor rekening dari daftar tersimpan)     | 0               | Transfer Kekayaan |         |  Minimum transfer adalah 1, mohon isikan kembali |
      | TC_WA_021 @Case_Negative7_NominalTransfer>Saldo(memilih nomor rekening dari daftar tersimpan) | 99993399800000  | Transfer Kekayaan |         |  Saldo Tidak Cukup, mohon isikan kembali         |
      | TC_WA_022 @Case_Negative8_EmptyNominalTransfer(memilih nomor rekening dari daftar tersimpan)  |                 | Transfer Kekayaan |         |  Nominal Tidak Boleh Kosong                      |
      | TC_WA_023 @Case_Positive4_EmptyCatatan(memilih nomor rekening dari daftar tersimpan)          | 1000            |                   | 120951  |  Transaksi Berhasil                              |
      | TC_WA_024 @Case_Negative9_InvalidPIN(memilih nomor rekening dari daftar tersimpan)            | 1000            | Transfer Kekayaan | 000000  |  PIN Anda Salah                                  |




  @TC_WA_025_sd_030 @SuccessOrFailedTransferByChoosingFromFavoriteList
  Scenario Outline: [TC_WA_025 sd TC_WA_030] Verify success or failed Transfer by choosing from the Favorite List
    Given user already login and is on the menu Transfer Antar BCA
    When user chooses from a favorite list to transfer money
    And user input nominal transfer "<NominalTransfer>" in the field
    And user input catatan "<Catatan>" in the field
    And user click Lanjutkan button after finished input the data
    And user click the Transfer button
    And user input pin "<PIN>" in the field
    And user click the Lanjutkan button
    Then validate the transfer is successful or failed and display the message "<expectedResult>"

    Examples:
      | ScenarioTitle                                                                                 | NominalTransfer | Catatan           | PIN     | expectedResult                                  |
      | TC_WA_025 @Case_Positive5_ValidData(memilih nomor rekening dari daftar favorite)              | 1000            | Transfer Kekayaan | 120951  | Transaksi Berhasil                              |
      | TC_WA_026 @Case_Negative10_NominalTransfer<1(memilih nomor rekening dari daftar favorite)     | 0               | Transfer Kekayaan | 120951  | Minimum transfer adalah 1, mohon isikan kembali |
      | TC_WA_027 @Case_Negative11_NominalTransfer>Saldo(memilih nomor rekening dari daftar favorite) | 99993399800000  | Transfer Kekayaan | 120951  | Saldo Tidak Cukup, mohon isikan kembali         |
      | TC_WA_028 @Case_Negative12_EmptyNominalTransfer(memilih nomor rekening dari daftar favorite)  |                 | Transfer Kekayaan | 120951  | Nominal Tidak Boleh Kosong                      |
      | TC_WA_029 @Case_Positive6_EmptyCatatan(memilih nomor rekening dari daftar favorite)           | 1000            |                   | 120951  | Transaksi Berhasil                              |
      | TC_WA_030 @Case_Negative13_InvalidPIN(memilih nomor rekening dari daftar favorite)            | 1000            | Transfer Kekayaan | 000000  | PIN Anda Salah                                  |



