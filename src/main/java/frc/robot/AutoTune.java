package frc.robot;

public class AutoTune {
    double[] encoderPositions;
    double[] motorOutputs;

    double P;
    double I;
    double D;

    double S;
    double V;
    double A;
    double G;
    
    public AutoTune(double[] encoderPositions, double[] motorOutputs) {
        this.encoderPositions = encoderPositions;
        this.motorOutputs = motorOutputs;
    }

    public void calculateFeedForward() {
        // double[] encoderDifferences = new double[encoderPositions.length - 1];
        // double[] motorOutputDifferences = new double[motorOutputs.length - 1];

        // for (int i = 1; i < encoderPositions.length; i++) {
        //     encoderDifferences[i - 1] = encoderPositions[i] - encoderPositions[i - 1];
        //     motorOutputDifferences[i - 1] = motorOutputs[i] - motorOutputs[i - 1];
        // }

        // double[] feedforwardTuneValues = new double[encoderDifferences.length];

        // for (int i = 0; i < encoderDifferences.length; i++) {
        //     if (encoderDifferences[i] != 0) {
        //         feedforwardTuneValues[i] = motorOutputDifferences[i] / encoderDifferences[i];
        //     } else {
        //         // Handle the case when the encoder difference is zero (avoid division by zero)
        //         feedforwardTuneValues[i] = 0.0; // or use a default value depending on your requirements
        //     }
        // }
        
        // return feedforwardTuneValues;
        
    }

    public void calculateFeedBack() {
        // This generates a PIDF loop based on the encoder and motor outputs in the line below.
        
    }
}
