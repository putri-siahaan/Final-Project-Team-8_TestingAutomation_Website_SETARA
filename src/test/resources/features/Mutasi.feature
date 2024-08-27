Feature: Verifikasi fitur Mutasi

  As a user
  I want to view my transaction history
  So that I can track and review all my financial activities.

  @TC_WA_061 @SuccessToSeeHistoryToday
  Scenario:[TC_WA_061] Verify success view transaction details history with filter "Today" [Semua Transaksi]
    Given user already login and is on the menu Mutasi page
    When user selects filter Hari ini and chooses Semua Transaksi
    Then user should see the transaction history for today

  @TC_WA_062 @Last7Days
  Scenario: [TC_WA_062] Verify success view user account transaction details history with filter "Last 7 Days" [Semua Transaksi]
    Given user already login and is on the menu Mutasi page
    When user selects filter 7 Hari Terakhir and chooses Semua Transaksi
    Then user should see the transaction history for the last 7 days

  @TC_WA_063 @Last15Days
  Scenario: [TC_WA_063] Verify success view user account transaction details history with filter "Last 15 Days" [Semua Transaksi]
    Given user already login and is on the menu Mutasi page
    When user selects filter 15 Hari Terakhir and chooses Semua Transaksi
    Then user should see the transaction history for the last 15 days

  @TC_WA_064 @Last1Month
  Scenario: [TC_WA_064] Verify success view user account transaction details history with filter "Last 1 Month" [Semua Transaksi]
    Given user already login and is on the menu Mutasi page
    When user selects filter 1 Bulan Terakhir and chooses Semua Transaksi
    Then user should see the transaction history for the last 1 month

  @TC_WA_065 @TanggalLain
  Scenario: [TC_WA_065] Verify success view user account transaction details history with filter "Tanggal Lain" [Semua Transaksi]
    Given user already login and is on the menu Mutasi page
    When user selects filter Tanggal Lain and chooses Semua Transaksi
    Then user should see the transaction history for the selected date

  @TC_WA_066 @MelihatBuktiDetailTransaksi
  Scenario: [TC_WA_066] Verify success to see proof of selected transaction details
    Given user already login and is on the menu Mutasi page
    When user clicks on a specific transaction to see the detail
    Then user should see the detailed transaction receipt and display "Bukti Transfer"
    And user click Download Bukti Transfer to store transaction evidence locally
    And verify Bukti Transfer success to save in local

  @TC_WA_067 @MelihatDataPemasukan
  Scenario: [TC_WA_067] Verify success to viewed "Pemasukan" Data in the Mutation Feature
    Given user already login and is on the menu Mutasi page
    When user chooses Pemasukan
    Then user should see the income transactions for today

  @TC_WA_068 @MelihatDataPengeluaranHariIni
  Scenario: [TC_WA_068] Verification successfully view the history of user account transaction details with the filter "Today" (Data Pengeluaran)
    Given user already login and is on the menu Mutasi page
    When user selects filter Hari ini and chooses Pengeluaran
    Then user should see the expense transactions for today

  @TC_WA_069 @MelihatDataPengeluaran7HariTerakhir
  Scenario: [TC_WA_069] Verification successfully view the history of user account transaction details with the filter "7 Hari Terakhir" (Data Pengeluaran)
    Given user already login and is on the menu Mutasi page
    When user selects filter 7 hari terakhir and chooses Pengeluaran
    Then user should see the expense transactions for today

  @TC_WA_070 @MelihatDataPengeluaran15HariTerakhir
  Scenario: [TC_WA_70] Verification successfully view the history of user account transaction details with the filter "15 Hari Terakhir" (Data Pengeluaran)
    Given user already login and is on the menu Mutasi page
    When user selects filter 15 hari terakhir and chooses Pengeluaran
    Then user should see the expense transactions for today

  @TC_WA_071 @MelihatDataPengeluaran1BulanTerakhir
  Scenario: [TC_WA_71] Verification successfully view the history of user account transaction details with the filter "1 Bulan Terakhir" (Data Pengeluaran)
    Given user already login and is on the menu Mutasi page
    When user selects filter 1 bulan terakhir and chooses Pengeluaran
    Then user should see the expense transactions for today

  @TC_WA_072 @MelihatDataPengeluaranDenganSettingTanggal
  Scenario: [TC_WA_72] Verification successfully view the history of user account transaction details with the filter "Tanggal Lain" (Data Pengeluaran)
    Given user already login and is on the menu Mutasi page
    When user selects filter setting Tanggal Lain and chooses Pengeluaran
    Then user should see the expense transactions for today

  @TC_WA_073 @DownloadAllTransaction
  Scenario: [TC_WA_73] Verify success to downloaded the entire transaction history
    Given user already login and is on the menu Mutasi page
    When user clicks on Download button
    Then the transaction history should be downloaded
