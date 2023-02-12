import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main
{
	public static JFrame frame = new JFrame("HackHERS");
	
	public static void main(String[] args)
	{		
//		JFrame frame = new JFrame("HackHERS"); 
		frame.setSize(1250, 650);
		frame.setLayout(null);

		GridLayout gl = new GridLayout(2, 4, 8, 8);
		JPanel optionsP = new JPanel();
		optionsP.setBounds(0, 0, 900, 650);
		optionsP.setLayout(gl);
		optionsP.setBackground(new Color(251, 245, 243));
		
		// individual department JPanels
		JPanel[] pageP = new JPanel[8];
		for (int p=0; p<8; p++)
			pageP[p] = new JPanel();

		JPanel receiptP = new JPanel();
		receiptP.setBounds(900, 0, 350, 650);
		receiptP.setLayout(null);
		receiptP.setBackground(new Color(251, 245, 243));
		JLabel rec = new JLabel("Order Receipt");
		rec.setBounds(127, 20, 100, 50);
		rec.setPreferredSize(new Dimension(100, 100));
		rec.setForeground(new Color(246, 141, 180));
		JTextArea rShow = new JTextArea("Click 'Update List' after selecting items!'");
		rShow.setBounds(10, 60, 275, 400);
		rShow.setBackground(new Color(251, 245, 243));
		rShow.setLayout(null);

		ArrayList<String> gList = new ArrayList<String>();
		ArrayList<String> dList = new ArrayList<String>();
		JButton finalize = new JButton ("Finalize!");
		finalize.setBounds(110, 550, 125, 25);
		finalize.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent finalize)
				{
					ArrayList<String> noDups = new ArrayList<String>();
					for (int x=0; x<dList.size(); x++)
					{
						if (!noDups.contains(dList.get(x))) // doesn't already contain category
							noDups.add(dList.get(x));
					}
					
					
					// finds most efficient path from furthest item from checkout -> closest item to checkout
					Dijkstra.neededDepartments(noDups);
					ArrayList<Node> nAList= Dijkstra.pathFinder();
					
					results(nAList, noDups); // final panel, passing information
					//results(nAList);
					receiptP.setVisible(false);
					optionsP.setVisible(false);
					for (int x=0; x<8; x++)
						pageP[x].setVisible(false);
				}
			}
		);

		// set up category buttons
		JButton[] categories = new JButton[8];
		String[] names = {"Grocery", "Produce", "Meat", "Dairy",
				"Bakery", "Seafood", "Frozen", "Floral"};
		for (int x=0; x<categories.length; x++)
		{
			categories[x] = new JButton(names[x]); 
			categories[x].setBackground(new Color(94, 116, 127));
			categories[x].setForeground(new Color(251, 245, 243));
			optionsP.add(categories[x]);
			categories[x].setVisible(true);
		}
		JButton back = new JButton("Back"); // returns to optionsP
		back.setBounds(25, 25, 75, 30);		
		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent back)
			{
				optionsP.setVisible(true);
				for (int x=0; x<8; x++)
					pageP[x].setVisible(false);
			}
		}
				);

		JButton addToR = new JButton("Update List!"); // display checked off
		addToR.setBounds(110, 515, 125, 25);
		addToR.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent add)
			{
				// access gList
				String text = "";
				for (int x=0; x<gList.size(); x++)
					text+=gList.get(x)+"\n";
				rShow.setText(text);
			}
		}
				);

		// individual panels and functions
		categories[0].addActionListener(new ActionListener() // grocery 
				{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[0].setBounds(0, 0, 900, 650);
				pageP[0].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[0].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[0].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[0].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[0].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[0].add(back);
				frame.add(pageP[0], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=0)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
				}
				);
		categories[1].addActionListener(new ActionListener() // produce 
				{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[1].setBounds(0, 0, 900, 650);
				pageP[1].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[1].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[1].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[1].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[1].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[1].add(back);
				frame.add(pageP[1], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=1)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
				}
				);
		categories[2].addActionListener(new ActionListener() // meat 
		{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[2].setBounds(0, 0, 900, 650);
				pageP[2].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[2].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[2].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[2].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[2].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[2].add(back);
				frame.add(pageP[2], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=2)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
		}
				);
		categories[3].addActionListener(new ActionListener() // dairy 
		{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[3].setBounds(0, 0, 900, 650);
				pageP[3].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[3].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[3].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[3].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[3].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[3].add(back);
				frame.add(pageP[3], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=3)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
		}
				);
		categories[4].addActionListener(new ActionListener() // bakery 
		{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[4].setBounds(0, 0, 900, 650);
				pageP[4].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[4].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[4].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[4].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[4].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[4].add(back);
				frame.add(pageP[4], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=4)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
		}
				);
		categories[5].addActionListener(new ActionListener() // seafood 
		{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[5].setBounds(0, 0, 900, 650);
				pageP[5].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[5].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[5].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[5].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[5].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[5].add(back);
				frame.add(pageP[5], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=5)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
		}
				);
		categories[6].addActionListener(new ActionListener() // frozen 
		{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[6].setBounds(0, 0, 900, 650);
				pageP[6].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[6].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[6].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[6].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[6].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[6].add(back);
				frame.add(pageP[6], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=6)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
		}
				);
		categories[7].addActionListener(new ActionListener() // floral 
		{
			public void actionPerformed(ActionEvent grocery)
			{
				pageP[7].setBounds(0, 0, 900, 650);
				pageP[7].setLayout(null);

				File f;
				try
				{ 
					f = new File(categories[7].getText()+"Items.txt");
					Scanner scan = new Scanner(f);
					scan.reset();
					int x = 60, y = 100;
					while (scan.hasNext())
					{
						String s = scan.nextLine();
						JCheckBox check = new JCheckBox(s);
						check.setBounds(x, y, 200, 50);
						y+=50;
						if (y>600)
						{
							x+=200;
							y=100;								
						}
						pageP[7].add(check);
						// if user checks off -> alter receipt back-end
						check.addItemListener(new ItemListener()
						{
							public void itemStateChanged(ItemEvent checked)
							{
								if (check.isSelected())
								{
									gList.add(check.getText());
									dList.add(categories[7].getText());
								}
								else if (!check.isSelected() && gList.contains(check.getText()))
								{
									gList.remove(check.getText());
									dList.remove(categories[7].getText());
								}
							}
						}
								);
					}
					scan.close();
				}
				catch (FileNotFoundException fe) {
					System.out.println("File Unavailable!");
				}

				pageP[7].add(back);
				frame.add(pageP[7], BorderLayout.WEST);

				for (int j=0; j<8; j++)
				{
					if (j!=7)
						pageP[j].setVisible(false);
					else
						pageP[j].setVisible(true);							
				}
				optionsP.setVisible(false);
			}
		}
				);
		
		receiptP.add(rec);
		receiptP.add(finalize);
		receiptP.add(addToR);
		receiptP.add(rShow);

		frame.add(optionsP, BorderLayout.CENTER);
		frame.add(receiptP, BorderLayout.WEST);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

		// unnecessary
//	// display results using a graph visualizer
//	public static void results(ArrayList<Node> path)
//	{
//		JPanel resultP = new JPanel();
//		resultP.setBounds(0, 0, 1250, 650);
//		resultP.setLayout(null);
//		resultP.setBackground(new Color(60, 60, 59));
//		
//		ImageIcon photo = new ImageIcon("FinalShopriteMap.png");
//		JLabel map = new JLabel(photo);
//		map.setBounds(0, 0, 1250, 650);
//		
//		JLabel[] steps = new JLabel[path.size()];
//		JLabel[] nums = new JLabel[path.size()];
//		
//		String s = "";
//		for (int i=0; i<path.size(); i++)
//		{
//			int xFactor = 40, yFactor = 38;
//			
//			steps[i] = new JLabel(new ImageIcon("smallCirc.png"));
//			nums[i] = new JLabel(""+(i+1));
//			
//			steps[i].setBounds((int)Math.round(path.get(i).x*xFactor), (int)Math.round(path.get(i).y*yFactor), 50, 50);
//			nums[i].setBounds((int)Math.round(path.get(i).x*xFactor)+22, (int)Math.round(path.get(i).y*yFactor), 50, 50);
//			nums[i].setPreferredSize(new Dimension(200, 200));
//			
//			// help set listed path
//			s += (nums[i].getText() + ". " + path.get(i).name + "\n");
//			
//			resultP.add(nums[i]);
//			resultP.add(steps[i]);
//		}
//		JTextArea directions = new JTextArea(s);
//		directions.setBounds(25, 25, 300, 270);
//		directions.setBackground(new Color(60, 60, 59));
//		directions.setForeground(new Color(246, 141, 180));
//		
//		resultP.add(directions);
//		resultP.add(map);
//		frame.add(resultP, BorderLayout.CENTER);
//		directions.setVisible(true);
//		resultP.setVisible(true);
//	}
	// display results using a graph visualizer
		public static void results(ArrayList<Node> path, ArrayList<String> noDups)
		{
			JPanel resultP = new JPanel();
			resultP.setBounds(0, 0, 1250, 650);
			resultP.setLayout(null);
			resultP.setBackground(new Color(60, 60, 59));
			
			ImageIcon photo = new ImageIcon("FinalShopriteMap.png");
			JLabel map = new JLabel(photo);
			map.setBounds(0, 0, 1250, 650);
			
			JLabel[] steps = new JLabel[path.size()];
			JLabel[] nums = new JLabel[path.size()];
			
			String s = "";
			for (int i=0; i<path.size(); i++)
			{
				int xFactor = 40, yFactor = 38;
				
				steps[i] = new JLabel(new ImageIcon("smallCirc.png"));
				nums[i] = new JLabel(""+(i+1));
				
				steps[i].setBounds((int)Math.round(path.get(i).x*xFactor), (int)Math.round(path.get(i).y*yFactor), 50, 50);
				nums[i].setBounds((int)Math.round(path.get(i).x*xFactor)+22, (int)Math.round(path.get(i).y*yFactor), 50, 50);
				nums[i].setPreferredSize(new Dimension(200, 200));
				
				// help set listed path
				s += (nums[i].getText() + ". " + path.get(i).name);
				
				//Added a little thing to tell the user whether or not they have to pick up an item at that location or not.
				//I starting trying to get which items they had to get at each location ubt it was sm work i gave up gosdnight <4
				if (noDups.contains(path.get(i).name)) {s += ", pick up items.\n";}
				else if (i != path.size()-1) {s += ", pass through to get to next department.\n";}
				
				resultP.add(nums[i]);
				resultP.add(steps[i]);
			}
			JTextArea directions = new JTextArea(s);
			directions.setBounds(25, 25, 300, 270);
			directions.setBackground(new Color(60, 60, 59));
			directions.setForeground(new Color(246, 141, 180));
			
			resultP.add(directions);
			resultP.add(map);
			frame.add(resultP, BorderLayout.CENTER);
			directions.setVisible(true);
			resultP.setVisible(true);
		}
}
