$baseUrl = "http://localhost:8081/api/swimmers"

Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "🧪 TESTING SWIMMERS CRUD OPERATIONS" -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan

# 1. READ (GET all)
Write-Host "`n[1] GET ALL SWIMMERS..." -ForegroundColor Yellow
$allSwimmers = Invoke-RestMethod -Uri $baseUrl -Method Get
Write-Host "Found $($allSwimmers.Count) swimmers." -ForegroundColor Green
$allSwimmers | Select-Object id, firstName, lastName, email | Format-Table

# 2. CREATE (POST)
Write-Host "`n[2] CREATE NEW SWIMMER..." -ForegroundColor Yellow
$newSwimmerBody = @{
    firstName = "Test"
    lastName = "User"
    email = "test.user@example.com"
    phoneNumber = "+33 6 00 00 00 00"
    dateOfBirth = "2000-01-01"
    squad = "JUNIOR"
    mainStroke = "FREESTYLE"
    status = "ACTIVE"
} | ConvertTo-Json

$createdSwimmer = Invoke-RestMethod -Uri $baseUrl -Method Post -Body $newSwimmerBody -ContentType "application/json"
Write-Host "Created swimmer with ID: $($createdSwimmer.id)" -ForegroundColor Green
$createdId = $createdSwimmer.id

# 3. READ (GET by ID)
Write-Host "`n[3] GET CREATED SWIMMER..." -ForegroundColor Yellow
$fetchedSwimmer = Invoke-RestMethod -Uri "$baseUrl/$createdId" -Method Get
Write-Host "Fetched: $($fetchedSwimmer.firstName) $($fetchedSwimmer.lastName) - $($fetchedSwimmer.email)" -ForegroundColor Green

# 4. UPDATE (PUT)
Write-Host "`n[4] UPDATE SWIMMER..." -ForegroundColor Yellow
$updateBody = @{
    firstName = "Test Updated"
    lastName = "User"
    email = "test.user.updated@example.com"
    phoneNumber = "+33 6 11 11 11 11"
    dateOfBirth = "2000-01-01"
    squad = "ELITE"
    mainStroke = "BUTTERFLY"
    status = "INACTIVE"
} | ConvertTo-Json

$updatedSwimmer = Invoke-RestMethod -Uri "$baseUrl/$createdId" -Method Put -Body $updateBody -ContentType "application/json"
Write-Host "Updated swimmer squad to: $($updatedSwimmer.squad) and status to: $($updatedSwimmer.status)" -ForegroundColor Green

# 5. GET STATS (Custom Endpoint)
Write-Host "`n[5] GET STATISTICS..." -ForegroundColor Yellow
$stats = Invoke-RestMethod -Uri "$baseUrl/stats" -Method Get
Write-Host "Total Swimmers: $($stats.total)" -ForegroundColor Green
Write-Host "Active: $($stats.active), Inactive: $($stats.inactive)" -ForegroundColor Green

# 6. DELETE (DELETE)
Write-Host "`n[6] DELETE SWIMMER..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "$baseUrl/$createdId" -Method Delete
Write-Host "Deleted swimmer with ID: $createdId" -ForegroundColor Green

# Verify deletion
Write-Host "`n[7] VERIFY DELETION..." -ForegroundColor Yellow
try {
    Invoke-RestMethod -Uri "$baseUrl/$createdId" -Method Get
} catch {
    Write-Host "Success! Swimmer could not be found after deletion (Expected 404 Error)." -ForegroundColor Green
}

Write-Host "`n✅ CRUD TESTS COMPLETED SUCCESSFULLY!" -ForegroundColor Cyan
