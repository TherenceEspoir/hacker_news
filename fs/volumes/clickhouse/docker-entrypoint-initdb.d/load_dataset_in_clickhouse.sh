#!/bin/bash
set -e
# Création de la table dans ClickHouse
clickhouse client -n <<-EOSQL
SET allow_experimental_json_type = 1;
# Crée une base de données dans ClickHouse
CREATE DATABASE IF NOT EXISTS analytics_db;

# Crée une table pour stocker les données du fichier JSON
CREATE TABLE IF NOT EXISTS analytics_db.construction_rehabilitation_data (
    code_region String,
    region String,
    annee_signature UInt16,
    construction_surface_mediane_des_operations_m2_su UInt32,
    construction_prix_de_revient_median_au_m2_des_operations UInt32,
    construction_prix_de_revient_median_des_operations_au_logement UInt32,
    rehabilitation_surface_mediane_des_operations_m2_su UInt32,
    rehabilitation_prix_de_revient_median_au_m2_des_operations UInt32,
    rehabilitation_prix_de_revient_median_des_operations_au_logement UInt32,
    geom JSON,
    centroid Nullable(String)
) ENGINE = MergeTree()
ORDER BY (code_region, annee_signature);
EOSQL

echo "liste fichier"
ls /docker-entrypoint-initdb.d

# Chemin vers le dataset JSON
DATASET_PATH="/docker-entrypoint-initdb.d/constructionrehabilitation_logementsocial_surface_prix.json"
ls /docker-entrypoint-initdb.d



# Vérifie si le fichier JSON existe avant de l'insérer dans ClickHouse
if [ -f "$DATASET_PATH" ]; then
    echo "📥 Importation des données JSON dans ClickHouse..."
    clickhouse-client --query="INSERT INTO analytics_db.construction_rehabilitation_data FORMAT JSONEachRow" < "$DATASET_PATH"
    echo "✅ Importation terminée avec succès."
else
    echo "❌ Le fichier $DATASET_PATH n'existe pas. Vérifiez le chemin et réessayez."
    exit 1
fi
