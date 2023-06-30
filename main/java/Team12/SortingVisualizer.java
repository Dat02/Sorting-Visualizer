package Team12;

import Team12.Sorting.BubbleSort;
import Team12.Sorting.MergeSort;
import Team12.Sorting.QuickSort;
import Team12.Sorting.SelectionSort;

public class SortingVisualizer {
    public static Thread sortingThread;
    public static VisualizeFrame frame;
    public static String typerSort;
    public static boolean pause = false;
    public static boolean endSort = false;
    public static boolean isSorting = false;

    public static void startSort(String typeSort) {
        SortingVisualizer.typerSort = typeSort;
        switch (typeSort) {
            case "Bubble Sort":
                sortingThread = new Thread(new BubbleSort(frame));
                break;
            case "Merge Sort":
                sortingThread = new Thread(new MergeSort(frame));
                break;
            case "Quick Sort":
                sortingThread = new Thread(new QuickSort(frame));
                break;
            case "Selection Sort":
                sortingThread = new Thread(new SelectionSort(frame));
                break;
            default:
                return;
        }
        isSorting = true;
        sortingThread.start();
    }

    public static void main(String[] args) {
        frame = new VisualizeFrame();
    }

    public static void endNow() {
        endSort = true;
        if(pause) conti();
    }

    public static void pause() {
        pause = true;
    }

    public static void conti() {
        pause = false;
        sortingThread.interrupt();
    }

    public static void checkPause(boolean pause) {
        SortingPanel.compareLabel.setText("Comparison: " + SortingPanel.compareNumber);
        if (pause) {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    public static void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void endSortSuccess() {
        frame.sortingPanel.successLabel.setVisible(true);
        MenuPanel.nextStep.setEnabled(false);
        MenuPanel.pauseButton.setEnabled(false);
        SubmitPanel.submitButton.setEnabled(true);
        SubmitPanel.submitTextField.setEnabled(true);
        MenuPanel.startButton.setText("Restart");
        MenuPanel.startButton.setEnabled(true);
        MenuPanel.endButton.setEnabled(false);
        pause = false;
        endSort = false;
        isSorting = false;
    }

    public static int getDelay() {
        if (endSort)
            return 0;
        else
            return MenuPanel.speedSlider.getValue();
    }
}
