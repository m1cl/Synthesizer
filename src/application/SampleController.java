package application;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.spi.MidiFileWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SampleController {
	public static Sequence mySeq;
	private int note;
	@FXML
	private TextField textFeld;
	@FXML
	private Button button;
	public static File myMidiFile;
	public static MidiFileWriter midi;

	@FXML
	public void makeSound(ActionEvent event) {
		button = (Button) event.getSource();
		note = Integer.parseInt(button.getId().substring(1, 3));		 // Cast the second and third part of the id from the button to integer value
		Synth.channel[0].noteOn(note, 600);								 // Put the button-ID to the value-parameter
		textFeld.setText(Synth.synth.getDefaultSoundbank().getName()); 	 // Put soundbank-name to TextField
	}

	@FXML
	public void record(ActionEvent event) {
		button = (Button) event.getSource();
		myMidiFile = new File("myMidiFile.mid"); 						 // Create a File with
		try {
			mySeq = new Sequence(Sequence.PPQ, 10);

			midi.write(mySeq, 0, myMidiFile);
			Track myTrack = mySeq.createTrack(); 						 // Create a Track for the sequence
			Synth.seq.setSequence(mySeq);								 // Set the sequence for the sequencer
			Synth.seq.recordEnable(myTrack, 0); 						 // Enable the recording of the Track
			Synth.seq.startRecording(); 								 // Starts the recording
		} catch (InvalidMidiDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void stopSequencer(ActionEvent event) {
		button = (Button) event.getSource();
	}

	@FXML
	public void play(ActionEvent event) {
		button = (Button) event.getSource();
		Synth.seq.start();
	}

	@FXML
	public void getList(ActionEvent event) {
		ChoiceBox cBox = (ChoiceBox) event.getSource();
	}
}