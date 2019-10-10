package util;

import org.beryx.textio.AbstractTextTerminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MockTextTerminal extends AbstractTextTerminal<MockTextTerminal> {
    public static final int DEFAULT_MAX_READS = 100;
    private final List<String> inputs = new ArrayList<>();
    private final StringBuilder outputBuilder = new StringBuilder();
    private int maxReads = DEFAULT_MAX_READS;
    private int inputIndex = -1;

    public static String stripAll(String text) {
        if (text == null) return null;
        return Arrays.stream(text.split("\\R"))
                .map(s -> s.replaceAll("\\t", ""))
                .map(s -> s.replaceAll("^\\s+|\\s+$", ""))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String read(boolean masking) {
        if (inputs.isEmpty()) throw new IllegalStateException("No entries available in the 'inputs' list");
        inputIndex++;
        if (inputIndex >= maxReads) throw new RuntimeException("Too many read calls");
        String val = inputs.get((inputIndex < inputs.size()) ? inputIndex : -1);
        outputBuilder.append(val).append('\n');
        return val;
    }

    @Override
    public void rawPrint(String message) {
        outputBuilder.append(message);
    }

    @Override
    public void println() {
        outputBuilder.append('\n');
    }

    public List<String> getInputs() {
        return inputs;
    }

    public String getOutput() {
        return stripAll(outputBuilder.toString());
    }

    public int getReadCalls() {
        return inputIndex + 1;
    }

    public int getMaxReads() {
        return maxReads;
    }

    public void setMaxReads(int maxReads) {
        this.maxReads = maxReads;
    }
}