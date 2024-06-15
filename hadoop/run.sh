#!/bin/bash

# Path to Hadoop installation
HADOOP_HOME=/usr/local/hadoop

# Input and output directories
INPUT_DIR=/path/to/input
OUTPUT_DIR=/path/to/output

# Remove previous output
$HADOOP_HOME/bin/hadoop fs -rm -r $OUTPUT_DIR

# Run Hadoop job
$HADOOP_HOME/bin/hadoop jar recommendation.jar Recommendation $INPUT_DIR $OUTPUT_DIR
