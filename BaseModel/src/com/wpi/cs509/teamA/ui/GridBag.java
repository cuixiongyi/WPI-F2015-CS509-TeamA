public MyFrame() throws IOException {
		JLabel l=new JLabel("lll");
		Icon icon=new ImageIcon("/Users/bangkura/Documents/workspace/UI/image.jpg");
		//在此直接创建对象
		l.setIcon(icon);
		this.setLayout(new GridLayout(1,2,4,4));
		JPanel col1 = new JPanel();
		col1.setSize(300,300);
		//col1.add(l);
		ArcsPanel arc = new ArcsPanel();

		col1.add(arc);
		JPanel col2 = new JPanel();
		JPanel col3 = new JPanel();
		col3.setLayout(new GridLayout(0,4,4,4));
		ButtonGroup group = new ButtonGroup();
		JRadioButton edge_radio = new JRadioButton("Edge");
		JRadioButton point_radio = new JRadioButton("Point");
		point_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = 1;
				System.out.println("point");
				arc.edge_end_point = null;
				arc.edge_start_point = null;
				arc.repaint();
			}
		});
		edge_radio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				arc.mode = 2;
				System.out.println("edge");
				arc.selected_point = null;
				arc.new_point = null;
				arc.repaint();
			}
		});
		group.add(edge_radio);
		group.add(point_radio);
		col3.add(edge_radio);
		col3.add(new JLabel(""));
		col3.add(point_radio);
		col3.add(new JLabel(""));
		col3.add(new JTextField("X-start"));
		col3.add(new JTextField("Y-start"));
		col3.add(new JTextField("X"));
		col3.add(new JTextField("Y"));
		col3.add(new JTextField("X-end"));
		col3.add(new JTextField("Y-end"));
		JButton point_add_button = new JButton("Add");
		col3.add(point_add_button);
		point_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(arc.new_point != null) {
					arc.list.add(arc.new_point);
					arc.new_point = null;
					arc.repaint();
				}
			}
			
		});
		col3.add(new JButton("Remove"));
		JButton edge_add_button = new JButton("Add");
		col3.add(edge_add_button);
		edge_add_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("ddddddd");
				if(arc.edge_end_point != null && arc.edge_start_point != null)
					arc.edge_list.add(new edge(arc.edge_start_point,arc.edge_end_point));
				arc.repaint();
			}
			
		});
		col3.add(new JButton("Remove"));
		//col2.setLayout(new GridLayout(0,1));
		JPanel col77 = new JPanel();
		col77.setLayout(new GridLayout(0,1,4,4));
		JLabel label = new JLabel("Select the building");
		label.setBounds(0,0,500,100);
		col77.add(label);
		JComboBox box = new JComboBox();
		box.setBounds(0,0,5000,100);
		col77.add(box);
		col2.add(col77);
		col2.add(col3);
		JPanel col4 = new JPanel();
		col4.setLayout(new GridLayout(0,2,4,4));
		col4.add(new JTextArea("Here display the points"));
		col4.add(new JTextArea("Here display the edges"));
		col4.setBounds(0, 0, 500, 300);
		col2.add(col4);
		this.add(col1);
		this.add(col2);