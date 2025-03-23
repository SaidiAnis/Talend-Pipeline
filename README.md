
# 📊 Talend Project - Data Integration for a Hypermarket

## 🧩 Project Goal

This project demonstrates a complete ETL pipeline using **Talend Open Studio**. It extracts Excel data from a hypermarket, transforms it into dimension and fact tables, removes duplicates, and loads everything into a **MySQL** database.

---

## 🧠 Overview of Job (`j_dwh`)

The main components of the job include:

- 📥 `tFileInputExcel_1`: Loads data from `Hypermarché.xlsx`
- 📥 `tFileInputExcel_2`: Loads data from `Objectifs_ventes.xlsx`
- 🧠 `tMap_1`: Data transformation, including lookup and calculated fields
- 🧾 `tUniqRow`: Deduplicates dimension data
- 📤 `tDBOutput`: Writes final tables to MySQL

---

## 📂 Input Files

- `C:/Data/Hypermarché.xlsx`
- `C:/Data/Objectifs_ventes.xlsx`

Both are read using the 2007 Excel format, from the first row and across all sheets.

---

## 🔄 Mapping Logic in tMap

### Input Flows
- `row1`: Main data from the hypermarket Excel
- `row2`: Lookup data from the sales objectives Excel

### Lookup Join
- Join Model: `Inner Join`
- Match Keys: `Categorie`, `Date_de_commande`, `Segment`

### Variables
- `prix = Double.parseDouble(row1.Montant_des_ventes)`

### Output Tables:
| Output Table   | Columns Used |
|----------------|--------------|
| `dim_client`   | ID_client, Nom_du_client, Region, Pays, Zone_geographique, Ville, Segment |
| `dim_commande` | ID_commande, Date_de_commande, Date_d_expedition, Mode_d_expedition |
| `dim_produit`  | ID_produit, Categorie, Sous_categorie, Nom_du_produit, Remise, prix |
| `table_fait`   | ID_ligne, ID_commande, ID_client, ID_produit, Montant_des_ventes, Profit, Quantite |

---

## 🛢️ MySQL Database Configuration

### 🐳 Run MySQL via Docker:

```bash
docker run --name talend-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=talend_db -p 3306:3306 -d mysql:latest
```

### 👤 Create user `berto` with permissions:

```sql
CREATE USER 'berto'@'%' IDENTIFIED BY 'bertomdp';
GRANT ALL PRIVILEGES ON talend_db.* TO 'berto'@'%';
FLUSH PRIVILEGES;
```

---

## 🔌 MySQL Connection in Talend

| Field       | Value       |
|-------------|-------------|
| Host        | localhost   |
| Port        | 3306        |
| Database    | talend_db   |
| Username    | berto       |
| Password    | bertomdp    |

> ❗ Make sure to **remove quotes** from `localhost`, `3306`, and table names in Talend.

---

## 🗃️ Output Tables in MySQL

- `Dim_client`
- `Dim_commande`
- `Dim_produit`
- `table_fait`

Each table is set to "Drop and Create" before insertion.

---

## 🚀 Execution Steps

1. Launch Docker MySQL container
2. Open Talend Studio and job `j_dwh`
3. Make sure the Excel files are available at `C:/Data/`
4. Run the job and check MySQL for results
---

## 🔚 Summary

This Talend project simulates a real-world data warehouse flow for a retail hypermarket. Data is extracted from Excel files, transformed into star schema structure (dimensions + fact), and persisted into MySQL.

