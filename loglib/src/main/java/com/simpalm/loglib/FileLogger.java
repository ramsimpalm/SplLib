package com.simpalm.loglib;

import android.content.Context;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to write logs in cache file
 */
public class FileLogger {
    public static final String FILE_BEGIN_MARKER = " ******************** ";
    public static final String NEW_LINE = "\n";
    private final String COLUMN_DIVIDER = "\t";
    private final String LOG_DIR = "logs";
    private final String FILE_EXT = ".log";
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyMMdd-HH:mm:ss.SSS");

    public enum LogType {
        V, D, I, W, E, EX, EI;

        @Override
        public String toString() {
            switch (this) {
                case V:
                    return "V";
                case D:
                    return "D";
                case I:
                    return "I";
                case W:
                    return "W";
                case E:
                    return "E";
                case EX:
                    return "EX";
                case EI:
                    return "EI";
                default:
                    return "V";
            }
        }

        public static LogType fromString(String type) {
            switch (type) {
                case "V":
                    return V;
                case "D":
                    return D;
                case "I":
                    return I;
                case "W":
                    return W;
                case "E":
                    return E;
                case "EX":
                    return EX;
                case "EI":
                    return EI;
                default:
                    return V;
            }
        }
    }

    private File dir, file;

    public FileLogger(Context context) {
        dir = new File(context.getCacheDir(), LOG_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
        file = new File(dir, SDF.format(new Date()) + FILE_EXT);
    }

    /**
     * Delete all log files
     */
    public void clearLogs() {
        for (File file : dir.listFiles()) {
            file.delete();
        }
    }

    /**
     * Append log in log in this session's log file
     * @param tag the tag
     * @param logType the log type
     * @param message the message
     */
    public void appendLog(String tag, LogType logType, String message) {
        StringBuilder builder = new StringBuilder();
        builder
                .append(SDF.format(new Date()))
                .append(COLUMN_DIVIDER)
                .append(tag)
                .append(COLUMN_DIVIDER)
                .append(logType.toString())
                .append(COLUMN_DIVIDER)
                .append(message)
                .append(NEW_LINE);
        FileOutputStream stream = null;
        OutputStreamWriter writer = null;
        try {
            stream = new FileOutputStream(file, true);
            writer = new OutputStreamWriter(stream);
            writer.append(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets all text logged until now
     * @return logs
     */
    public String getAllLogs() {
        StringBuilder builder = new StringBuilder();
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[files.length - i - 1];
            builder.append(FILE_BEGIN_MARKER)
                    .append(file.getName())
                    .append(FILE_BEGIN_MARKER)
                    .append(NEW_LINE)
                    .append(getFileContent(file))
                    .append(NEW_LINE);
        }
        return builder.toString();
    }

    /**
     * Read file in string
     * @param file the file to read
     * @return file content
     */
    private String getFileContent(File file) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(NEW_LINE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
