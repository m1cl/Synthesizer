package application;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

public class Synth {
	public static  Synthesizer synth;
	public static Sequencer seq;
	public static Transmitter seqTrans;
	public static Instrument[] instr;
	public static Receiver synthRcvr;
	private static int note;
	public static MidiChannel[] channel;
	public static void createSynth(){
        try {
                seq = MidiSystem.getSequencer(); 					// Create the sequencer
                seqTrans = seq.getTransmitter();					// Set the transmitter for the sequencer
                synth = MidiSystem.getSynthesizer();				// Create the synthesizer
                synthRcvr = synth.getReceiver();					// Set the synth as the receiver
                seqTrans.setReceiver(synthRcvr);					// Set the receiver for the transmitter
                synth.open();										// Open the synthesizer-object to avoid exceptions
                seq.open();											// Open the sequencer-object " 		" 		"
                Instrument[] instr = synth.getDefaultSoundbank().getInstruments(); // Set the soundbank for the synthesizer

                synth.loadInstrument(instr[26]);					// Set a particular instrument for the synth
                channel  = synth.getChannels();						// Get the channel of the synthesizer
                }catch (MidiUnavailableException e) {
                        // TODO Auto-generated catch block
                e.printStackTrace();
        }
	}
}
