# Postman

## step

1. Register an account
2. Login and get a token
3. Book room and cancel room need to use your token
4. Get room doesn't need token

### Register

    curl --location --request POST 'http://localhost:8080/user/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "username": "test",
        "password": "test123"
    }'

### Login
    curl --location --request POST 'http://localhost:8080/user/authenticate' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "username": "test",
    "password": "test123"
    }'

### Get available rooms
    curl --location --request GET 'http://localhost:8080/room?type=AVAILABLE'

### Get booked rooms
    curl --location --request GET 'http://localhost:8080/room?type=BOOKED'

### Book room
    curl --location --request PUT 'http://localhost:8080/room/book' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdGV2ZW4iLCJleHAiOjE2NzgxNTQzNDQsImlhdCI6MTY3ODEzNjM0NH0.YDX1BdHj228_8N1skdYnpP8t-40X2fkeMh6x1WgLAC3VubbJxFXShIJtnNC5-lJ__k58B1kI67yrs0ZqR3frIA' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userId": 1,
    "id": 2
    }'

### Cancel room
    curl --location --request PUT 'http://localhost:8080/room/cancel' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdGV2ZW4iLCJleHAiOjE2NzgxNTQzNDQsImlhdCI6MTY3ODEzNjM0NH0.YDX1BdHj228_8N1skdYnpP8t-40X2fkeMh6x1WgLAC3VubbJxFXShIJtnNC5-lJ__k58B1kI67yrs0ZqR3frIA' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userId": 1
    }'