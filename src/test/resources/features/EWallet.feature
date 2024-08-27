@EWallet_TopUp
Feature: EWallet Top Up

    As a user
    I want to top up
    So that I can easily send money to other Ewallet

  @TC_WA_031_sd_038 @SuccessOrFailedTopUpToNewDestinationNumber
  Scenario Outline: [TC_WA_031 s/d TC_WA_038] Verify success or failed top up to new destination number
    Given user already login and is on the menu Top Up EWallet
    When user click the TransferKeTujuanBaru button
    And user input nomor ewallet baru "<NomorEWalletBaru>" in the field
    And user click the CariNomor button
    And user choose the SumberRekening
    And user input nominal top up "<NominalTopUp>" in the field
    And user input the catatan "<Catatan>" in the field
    And user click the Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "<PIN>" in the field
    And user click Lanjutkan button
    Then validate the top up is successful or failed and display the message "<expectedResult>"

    Examples:
      | ScenarioTitle                                   | NomorEWalletBaru  | NominalTopUp    | Catatan           | PIN     | expectedResult                                        |
      | TC_WA_031 @Case_Positive1_ValidData             | 081234567891      | 10000           | Top Up Moneyyy    | 120951  | Transaksi Berhasil                                    |
      | TC_WA_032 @Case_Negative1_InvalidNoTujuan       | 012458292819      |                 |                   |         | NOMOR TIDAK TERDAFTAR                                 |
      | TC_WA_033 @Case_Negative2_EmptyNoTujuan         |                   |                 |                   |         | Nomor Tidak Boleh Kosong                              |
      | TC_WA_034 @Case_Negative3_EmptyNominalTransfer  | 081234567891      |                 | Top Up Moneyyy    |         | Nominal Tidak Boleh Kosong                            |
      | TC_WA_035 @Case_Negative4_NominalTransfer<10000 | 081234567891      | 5000            |                   |         | Minimum transfer adalah 10.000, mohon isikan kembali  |
      | TC_WA_036 @Case_Negative5_NominalTransfer>Saldo | 081234567891      | 100000000000    |                   |         | Saldo Tidak Cukup, mohon isikan kembali               |
      | TC_WA_037 @Case_Positive2_EmptyCatatan          | 081234567891      | 10000           |                   | 120951  | Transaksi Berhasil                                    |
      | TC_WA_038 @Case_Negative6_InvalidPIN            | 081234567891      | 10000           | Top Up Moneyyy    | 000000  | PIN Anda Salah                                       |

  @TC_WA_039_sd_044 @SuccessOrFailedTopUpByChoosingFromSavedList
  Scenario Outline: [TC_WA_039 sd TC_WA_044] Verify success or failed Top Up by choosing from the Saved List
    Given user already login and is on the menu Top Up EWallet
    When user chooses from a saved list to top up money
    And user input nominal top up "<NominalTopUp>" in the field
    And user input the catatan "<Catatan>" in the field
    And user click Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "<PIN>" in the field
    And user click Lanjutkan button
    Then validate the top up is successful or failed and display the message "<expectedResult>"

    Examples:
      | ScenarioTitle                                                                                 | NominalTopUp    | Catatan           | PIN     |  expectedResult                                       |
      | TC_WA_039 @Case_Positive3_ValidData(memilih nomor rekening dari daftar tersimpan)             | 10000           | Top Up Moneyyy    | 120951  |  Transaksi Berhasil                                   |
      | TC_WA_040 @Case_Negative7_NominalTransfer<10k(memilih nomor rekening dari daftar tersimpan)   | 5000            | Top Up Moneyyy    |         |  Minimum transfer adalah 10.000, mohon isikan kembali |
      | TC_WA_041 @Case_Negative8_NominalTransfer>Saldo(memilih nomor rekening dari daftar tersimpan) | 1000000000000   | Top Up Moneyyy    |         |  Saldo Tidak Cukup, mohon isikan kembali              |
      | TC_WA_042 @Case_Negative9_EmptyNominalTransfer(memilih nomor rekening dari daftar tersimpan)  |                 | Top Up Moneyyy    |         |  Nominal Tidak Boleh Kosong                           |
      | TC_WA_043 @Case_Positive4_EmptyCatatan(memilih nomor rekening dari daftar tersimpan)          | 10000           |                   | 120951  |  Transaksi Berhasil                                   |
      | TC_WA_044 @Case_Negative10_InvalidPIN(memilih nomor rekening dari daftar tersimpan)           | 10000           | Top Up Moneyyy    | 000000  |  PIN Anda Salah                                       |




  @TC_WA_045_sd_050 @SuccessOrFailedTopUpByChoosingFromFavoriteList
  Scenario Outline: [TC_WA_045 sd TC_WA_050] Verify success or failed Top Up by choosing from the Favorite List
    Given user already login and is on the menu Top Up EWallet
    When user chooses from a favorite list to top up money
    And user input nominal top up "<NominalTopUp>" in the field
    And user input the catatan "<Catatan>" in the field
    And user click Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "<PIN>" in the field
    And user click Lanjutkan button
    Then validate the top up is successful or failed and display the message "<expectedResult>"

    Examples:
      | ScenarioTitle                                                                                 | NominalTopUp    | Catatan           | PIN     | expectedResult                                        |
      | TC_WA_045 @Case_Positive5_ValidData(memilih nomor rekening dari daftar favorite)              | 10000           | Top Up Moneyyy    | 120951  | Transaksi Berhasil                                    |
      | TC_WA_046 @Case_Negative11_NominalTransfer<10k(memilih nomor rekening dari daftar favorite)   | 5000            | Top Up Moneyyy    | 120951  | Minimum transfer adalah 10.000, mohon isikan kembali  |
      | TC_WA_047 @Case_Negative12_NominalTransfer>Saldo(memilih nomor rekening dari daftar favorite) | 99993399800000  | Top Up Moneyyy    | 120951  | Saldo Tidak Cukup, mohon isikan kembali               |
      | TC_WA_048 @Case_Negative13_EmptyNominalTopUp(memilih nomor rekening dari daftar favorite)     |                 | Top Up Moneyyy    | 120951  | Nominal Tidak Boleh Kosong                            |
      | TC_WA_049 @Case_Positive6_EmptyCatatan(memilih nomor rekening dari daftar favorite)           | 10000           |                   | 120951  | Transaksi Berhasil                                    |
      | TC_WA_050 @Case_Negative14_InvalidPIN(memilih nomor rekening dari daftar favorite)            | 10000           | Top Up Moneyyy    | 000000  | PIN Anda Salah                                        |

  @TC_WA_051 @SuccessSavedAccountOvoToSavedList
  Scenario: [TC_WA_051] Verify success saved Account Ovo To Saved List
    Given user already login and is on the menu Top Up EWallet
    When user click the TransferKeTujuanBaru button
    And user input nomor ewallet baru "081234567891" in the field
    And user click the CariNomor button
    And user checklist box to add to saved list
    And user choose the SumberRekening
    And user input nominal top up "10000" in the field
    And user input the catatan "Top Up Moneyyy" in the field
    And user click the Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "120951" in the field
    And user click Lanjutkan button
    And user click Button Kembali Ke Homepage
    And user click eWallet Menu and select ovo
    Then validate success to saved Account EWallet with account "081234567891"

  @TC_WA_052 @SuccessToFavoriteAccountOvo
  Scenario: [TC_WA_052] Verify success to favorite Account Ovo
    Given user already login and is on the menu Top Up EWallet
    When user click love from Saved List with account 081234567891
    Then validate success to favorite EWallet with account "081234567891"


  @TC_WA_053 @SuccessSavedAccountShopeePayToSavedList
  Scenario: [TC_WA_053] Verify success saved Account ShopeePay To Saved List
    Given user already login and is on the menu EWallet and choose ShopeePay
    When user click the TransferKeTujuanBaru button
    And user input nomor ewallet baru "081234567891" in the field
    And user click the CariNomor button
    And user checklist box to add to saved list
    And user choose the SumberRekening
    And user input nominal top up "10000" in the field
    And user input the catatan "Top Up Moneyyy" in the field
    And user click the Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "120951" in the field
    And user click Lanjutkan button
    And user click Button Kembali Ke Homepage
    And user click eWallet Menu and select shopeepay
    Then validate success to saved Account EWallet with account "081234567891"

  @TC_WA_054 @SuccessToFavoriteAccountShopeePay
  Scenario: [TC_WA_054] Verify success to favorite Account ShopeePay
    Given user already login and is on the menu EWallet and choose ShopeePay
    When user click love from Saved List with account 081234567891
    Then validate success to favorite EWallet with account "081234567891"


  @TC_WA_055 @SuccessSavedAccountGoPayToSavedList
  Scenario: [TC_WA_055] Verify success saved Account GoPay To Saved List
    Given user already login and is on the menu EWallet and choose Gopay
    When user click the TransferKeTujuanBaru button
    And user input nomor ewallet baru "081234567891" in the field
    And user click the CariNomor button
    And user checklist box to add to saved list
    And user choose the SumberRekening
    And user input nominal top up "10000" in the field
    And user input the catatan "Top Up Moneyyy" in the field
    And user click the Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "120951" in the field
    And user click Lanjutkan button
    And user click Button Kembali Ke Homepage
    And user click eWallet Menu and select Gopay
    Then validate success to saved Account EWallet with account "081234567891"

  @TC_WA_056 @SuccessToFavoriteAccountGopay
  Scenario: [TC_WA_056] Verify success to favorite Account GoPay
    Given user already login and is on the menu EWallet and choose Gopay
    When user click love from Saved List with account 081234567891
    Then validate success to favorite EWallet with account "081234567891"

  @TC_WA_057 @SuccessSavedAccountDanaToSavedList
  Scenario: [TC_WA_057] Verify success saved Account Dana To Saved List
    Given user already login and is on the menu Top Up EWalled and choose Dana
    When user click the TransferKeTujuanBaru button
    And user input nomor ewallet baru "081234567891" in the field
    And user click the CariNomor button
    And user checklist box to add to saved list
    And user choose the SumberRekening
    And user input nominal top up "10000" in the field
    And user input the catatan "Top Up Moneyyy" in the field
    And user click the Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "120951" in the field
    And user click Lanjutkan button
    And user click Button Kembali Ke Homepage
    And user click eWallet Menu and select dana
    Then validate success to saved Account EWallet with account "081234567891"

  @TC_WA_058 @SuccessToFavoriteAccountDana
  Scenario: [TC_WA_058] Verify success to favorite Account Dana
    Given user already login and is on the menu Top Up EWalled and choose Dana
    When user click love from Saved List with account 081234567891
    Then validate success to favorite EWallet with account "081234567891"

  @TC_WA_059 @SuccessSavedAccountLinkAjaToSavedList
  Scenario: [TC_WA_059] Verify success saved Account LinkAja To Saved List
    Given user already login and is on the menu EWallet and choose Link Aja
    When user click the TransferKeTujuanBaru button
    And user input nomor ewallet baru "081234567891" in the field
    And user click the CariNomor button
    And user checklist box to add to saved list
    And user choose the SumberRekening
    And user input nominal top up "10000" in the field
    And user input the catatan "Top Up Moneyyy" in the field
    And user click the Lanjutkan button after finished input the data top up
    And user click Top Up button
    And user input the pin "120951" in the field
    And user click Lanjutkan button
    And user click Button Kembali Ke Homepage
    And user click eWallet Menu and select linkAja
    Then validate success to saved Account EWallet with account "081234567891"

  @TC_WA_060 @SuccessToFavoriteAccountLinkAja
  Scenario: [TC_WA_060] Verify success to favorite Account LinkAja
    Given user already login and is on the menu EWallet and choose Link Aja
    When user click love from Saved List with account 081234567891
    Then validate success to favorite EWallet with account "081234567891"