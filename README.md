# Banking Management
O projeto se consiste de uma API CRUD de banco, onde interage diretamente com o banco de dados MySql para poder fazer as transações bancárias

# Documentação do CRUD
![image](https://github.com/MrAranha/BankingManagement/assets/101605425/3d8c99a4-9c0f-4924-bf7b-884737eb6647)
![image](https://github.com/MrAranha/BankingManagement/assets/101605425/56093ddb-3a19-46f0-9688-ee29ec494987)

O Delete, Update e Insert podem ser feitos por essa aba
//Pra deletar precisa colocar data e um valor por não ter sido tratado um valor nulo no campo do TypeScript
-----------------------------------------------------------------------------------------------------------
![image](https://github.com/MrAranha/BankingManagement/assets/101605425/b1231476-ff08-4293-ab61-f940bd2cd8e2)
![image](https://github.com/MrAranha/BankingManagement/assets/101605425/f4a72ae9-40c9-4f50-958f-a0297ec2ed5c)
Toda a consulta e Filtro podem ser realizador por essa aba

# UML do Projeto

![image](https://github.com/MrAranha/BankingManagement/assets/101605425/d534933a-78f3-4fab-8e79-b290c4afd04d)


# Crie uma database MySql com o nome de "banking" e cole isso no banco para pleno funcionamento da aplicação

```
CREATE TABLE `sample_person` (
  `vversion` int DEFAULT NULL,
  `id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `rrole` varchar(255) DEFAULT NULL,
  `important` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `application_user` (
  `vversion` int DEFAULT NULL,
  `id` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `hashed_password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `transactionhistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Receiver` varchar(255) DEFAULT NULL,
  `Sender` varchar(255) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `MoneySent` decimal(10,0) DEFAULT NULL,
  `important` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user_roles` (
  `user_id` varchar(255) DEFAULT NULL,
  `rroles` char(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `BALANCE` decimal(10,0) DEFAULT NULL,
  `isADMIN` tinyint(1) NOT NULL,
  `uLogin` varchar(255) NOT NULL,
  `uPassword` varchar(255) NOT NULL,
  `uName` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `transactionhistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Receiver` varchar(255) DEFAULT NULL,
  `Sender` varchar(255) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `MoneySent` decimal(10,0) DEFAULT NULL,
  `important` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
    insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values ('Joe Banaca', 'Patrick Braw', STR_TO_DATE('1-01-2012', '%d-%m-%Y'), 312313.02, false)
```
