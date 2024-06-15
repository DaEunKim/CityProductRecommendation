import psycopg2
import csv

def load_to_postgresql(file_path):
    conn = psycopg2.connect(
        dbname="your_db",
        user="your_user",
        password="your_password",
        host="your_host",
        port="your_port"
    )
    cursor = conn.cursor()

    with open(file_path, 'r') as f:
        reader = csv.reader(f)
        next(reader)  # Skip the header row
        for row in reader:
            cursor.execute("INSERT INTO recommendations (city, item_id, score) VALUES (%s, %s, %s)",
                           (row[0], row[1], row[2]))

    conn.commit()
    cursor.close()
    conn.close()

# Load data
load_to_postgresql('path/to/recommendations.csv')
