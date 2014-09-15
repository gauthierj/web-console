package org.jacques.gauthier.webconsole;

import org.apache.commons.exec.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by gja on 9/12/14.
 */
@Service
public class ExecutionService {

    private final Executor executor = new DefaultExecutor();

    public ExecutionService() {
        executor.setWatchdog(new ExecuteWatchdog(100000));

    }

    public String execute(String command) {
        if(StringUtils.isBlank(command)) {
            return "";
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            CommandLine cmd = CommandLine.parse(command);
            executor.setStreamHandler(new PumpStreamHandler(out));
            executor.execute(cmd);
        } catch (IOException e) {
            if(e.getCause() != null) {
                return e.getCause().getMessage();
            }
        }
        return new String(out.toByteArray());
    }
}
