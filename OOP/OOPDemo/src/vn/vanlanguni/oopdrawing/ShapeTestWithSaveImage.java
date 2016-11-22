package vn.vanlanguni.oopdrawing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class ShapeTestWithSaveImage extends JFrame {
	private final int _HEIGHT = 600;
	private final int _WIDTH = 600;
	ArrayList<Shape> list;
	Canvas pnl;
	BufferedImage image;
	boolean leftDrag, rightDrag;
	Color[] ballColorArray = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY, Color.PINK, Color.BLACK };

	public ShapeTestWithSaveImage() {
		setPreferredSize(new Dimension(600, 600));
		list = new ArrayList<>();
		pnl = new Canvas();
		setContentPane(pnl);
		pack();
		image = new BufferedImage(_WIDTH, _HEIGHT, BufferedImage.TYPE_INT_ARGB);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDraw = new JMenu("Draw");
		menuBar.add(mnDraw);

		JMenuItem mntmSave = new JMenuItem("Save drawing image");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mnDraw.add(mntmSave);

		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		mnDraw.add(mntmClear);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		mnEdit.add(mntmUndo);

		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fchSave = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "png");
				fchSave.setFileFilter(filter);
				int result = fchSave.showSaveDialog(ShapeTestWithSaveImage.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						if (image != null) {
							ImageIO.write(image, "PNG", fchSave.getSelectedFile());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		mntmClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list.clear();
				image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				repaint();
			}
		});

		mntmUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (list != null && list.size() > 0) {
					int lastIndex = list.size() - 1;
					list.remove(lastIndex);
					image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
					repaint();
				}
			}
		});
	}

	public static void main(String[] args) {
		ShapeTestWithSaveImage f = new ShapeTestWithSaveImage();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setTitle("OOP Demo");
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	private class Canvas extends JPanel implements MouseListener {
		public Canvas() {
			addMouseListener(this);
			setFocusable(true);
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
			Timer t = new Timer(50, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Random rnd = new Random();
					Color shapeColor = ballColorArray[rnd.nextInt(ballColorArray.length)];
					Shape s;
					Point p = getMousePosition();
					if (p != null && leftDrag) {
						s = new Square(50, getMousePosition());
						s.setColor(shapeColor);
						list.add(s);
						repaint();
					} else if (p != null && rightDrag) {
						s = new Circle(25, getMousePosition());
						s.setColor(shapeColor);
						list.add(s);
						repaint();
					}

				}
			});
			t.start();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics g2 = image.createGraphics();
			for (Iterator<Shape> iterator = list.iterator(); iterator.hasNext();) {
				Shape shape = (Shape) iterator.next();
				shape.draw(g2);
			}
			g.drawImage(image, 0, 0, null);
			g2.dispose();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// Shape s;
			// //System.out.println(e.toString());
			// if (SwingUtilities.isLeftMouseButton(e)) {
			// s = new Square(50, e.getPoint());
			// s.setColor(Color.BLUE);
			// } else {
			// s = new Circle(25, e.getPoint());
			// s.setColor(Color.RED);
			// }
			// if (s != null) {
			// list.add(s);
			// }
			// repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				leftDrag = true;
			} else if (SwingUtilities.isRightMouseButton(e)) {
				rightDrag = true;
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				leftDrag = false;
			} else if (SwingUtilities.isRightMouseButton(e)) {
				rightDrag = false;
			}

		}
	}

}
