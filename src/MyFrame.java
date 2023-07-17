import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame
{
 
 Calendar calendar;
 SimpleDateFormat timeFormat;
 SimpleDateFormat dayFormat;
 SimpleDateFormat dateFormat;
 JLabel timeLabel;
 JLabel dayLabel;
 JLabel dateLabel;
 String time;
 String day;
 String date;

 MyFrame()
{
	  this.setTitle("My Clock Program");
	  this.setLayout(new FlowLayout());
	  this.setSize(500,300);
	  this.setResizable(true);
	  
	  timeFormat = new SimpleDateFormat("hh:mm:ss a");
	  dayFormat = new SimpleDateFormat("EEEE");
	  dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
	  
	  timeLabel = new JLabel();
	  timeLabel.setFont(new Font("Verdana",Font.PLAIN,60));
	  timeLabel.setForeground(new Color(0x00FF00));
	  timeLabel.setBackground(Color.black);
	  timeLabel.setOpaque(true);
	  
	  dayLabel = new JLabel();
	  dayLabel.setFont(new Font("Verdana",Font.PLAIN,35));
	  dayLabel.setForeground(new Color(0x00FF));
	  
	  dateLabel = new JLabel();
	  dateLabel.setFont(new Font("Verdana",Font.PLAIN,35));
	  dateLabel.setForeground(new Color(0x00FF));
	  
	  JButton jb = new JButton();
	  jb.setText("Stop Watch");
	  jb.setBorder(new EmptyBorder(30,20,20,50));
	  
	  JButton jb1 = new JButton();
	  jb1.setText("Set Alarm");
	  jb1.setBorder(new EmptyBorder(30,20,20,50));
	  
	  
	  this.add(timeLabel);
	  this.add(dayLabel);
	  this.add(dateLabel);
	  this.add(jb,BorderLayout.SOUTH);
	  this.add(jb1,BorderLayout.SOUTH);
	  this.setVisible(true);
	  
		jb.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Stopwatch sw = new Stopwatch();
				//Alarm al = new Alarm();
				setVisible(false);
			}
		});
		jb1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AlarmClock al = new AlarmClock();
				 SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			                new AlarmClock().setVisible(true);
			            }
			        });
				//Alarm al = new Alarm();
//				setVisible(false);
			}
		});
		
	  setTime();
 }
 
	public void setTime() 
	{
		while(true) 
		{
			time = timeFormat.format(Calendar.getInstance().getTime());
			timeLabel.setText(time);
			  
			day = dayFormat.format(Calendar.getInstance().getTime());
			dayLabel.setText(day);
			  
			date = dateFormat.format(Calendar.getInstance().getTime());
			dateLabel.setText(date);
			  
			try 
			{
			    Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}