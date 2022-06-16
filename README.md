# hs-tracker-api 🔎

This service is a complement of ***HearthStone Tracker Project*** project.

_hs-tracker-api_ has an objective is to manage user first place victories according to specific hero to conquer all achievements.


## Execution

Start localstack running `docker-compose up --build` in root folder.

Then run script in ```./scripts``` folder using command below:

```curl
./create-heroes.sh heroes.txt
```

This command will populate your database with blizzard portrait image of every hero saved in ```heroes.txt```