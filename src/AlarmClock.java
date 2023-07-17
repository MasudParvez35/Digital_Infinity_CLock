import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.io.File;
import java.io.IOException;

public class AlarmClock extends JFrame 
{
    private JTextField hourTextField;
    private JTextField minuteTextField;
    private JButton setButton;

    public AlarmClock() {
        setTitle("Alarm Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        hourTextField = new JTextField(2);
        minuteTextField = new JTextField(2);
        setButton = new JButton("Set Alarm");

        setButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int hour = Integer.parseInt(hourTextField.getText());
                int minute = Integer.parseInt(minuteTextField.getText());

                LocalTime alarmTime = LocalTime.of(hour, minute);
                LocalTime currentTime = LocalTime.now();

                long delay = calculateDelay(currentTime, alarmTime);

                Timer timer = new Timer((int) delay, new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        playAlarmSound();
                        JOptionPane.showMessageDialog(null, "Wake up!");
                    }
                });

                timer.setRepeats(false);
                timer.start();
            }
        });

        add(new JLabel("Hour:"));
        add(hourTextField);
        add(new JLabel("Minute:"));
        add(minuteTextField);
        add(setButton);
    }

    private long calculateDelay(LocalTime currentTime, LocalTime alarmTime) 
    {
        long currentSeconds = currentTime.toSecondOfDay();
        long alarmSeconds = alarmTime.toSecondOfDay();

        long delay;
        if (alarmSeconds >= currentSeconds) 
        {
            delay = (alarmSeconds - currentSeconds) * 1000;
        } else {
            long remainingSeconds = 24 * 60 * 60 - currentSeconds;
            delay = (remainingSeconds + alarmSeconds) * 1000;
        }

        return delay;
    }

    private void playAlarmSound() 
    {
        try 
        {
            File soundFile = new File("alarm.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
