package gui.transaction;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qora.crypto.Base58;
import qora.transaction.ArbitraryTransaction;
import utils.DateTimeFormat;

@SuppressWarnings("serial")
public class ArbitraryTransactionDetailsFrame extends JFrame
{
	public ArbitraryTransactionDetailsFrame(ArbitraryTransaction arbitraryTransaction)
	{
		super("Qora - Transaction Details");
		
		//ICON
		List<Image> icons = new ArrayList<Image>();
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon16.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon32.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon64.png"));
		icons.add(Toolkit.getDefaultToolkit().getImage("images/icons/icon128.png"));
		this.setIconImages(icons);
		
		//CLOSE
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//LAYOUT
		this.setLayout(new GridBagLayout());
		
		//PADDING
		((JComponent) this.getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//LABEL GBC
		GridBagConstraints labelGBC = new GridBagConstraints();
		labelGBC.insets = new Insets(0, 5, 5, 0);
		labelGBC.fill = GridBagConstraints.HORIZONTAL;   
		labelGBC.anchor = GridBagConstraints.NORTHWEST;
		labelGBC.weightx = 0;	
		labelGBC.gridx = 0;
		
		//DETAIL GBC
		GridBagConstraints detailGBC = new GridBagConstraints();
		detailGBC.insets = new Insets(0, 5, 5, 0);
		detailGBC.fill = GridBagConstraints.HORIZONTAL;  
		detailGBC.anchor = GridBagConstraints.NORTHWEST;
		detailGBC.weightx = 1;	
		detailGBC.gridwidth = 2;
		detailGBC.gridx = 1;		
		
		//LABEL TYPE
		labelGBC.gridy = 0;
		JLabel typeLabel = new JLabel("Type:");
		this.add(typeLabel, labelGBC);
						
		//TYPE
		detailGBC.gridy = 0;
		JLabel type = new JLabel("Arbitrary Transaction");
		this.add(type, detailGBC);
		
		//LABEL SIGNATURE
		labelGBC.gridy = 1;
		JLabel signatureLabel = new JLabel("Signature:");
		this.add(signatureLabel, labelGBC);
				
		//SIGNATURE
		detailGBC.gridy = 1;
		JTextField signature = new JTextField(Base58.encode(arbitraryTransaction.getSignature()));
		signature.setEditable(false);
		this.add(signature, detailGBC);
		
		//LABEL REFERENCE
		labelGBC.gridy = 2;
		JLabel referenceLabel = new JLabel("Reference:");
		this.add(referenceLabel, labelGBC);
						
		//REFERENCE
		detailGBC.gridy = 2;
		JTextField reference = new JTextField(Base58.encode(arbitraryTransaction.getReference()));
		reference.setEditable(false);
		this.add(reference, detailGBC);
		
		//LABEL TIMESTAMP
		labelGBC.gridy = 3;
		JLabel timestampLabel = new JLabel("Timestamp:");
		this.add(timestampLabel, labelGBC);
						
		//TIMESTAMP
		detailGBC.gridy = 3;
		JLabel timestamp = new JLabel(DateTimeFormat.timestamptoString(arbitraryTransaction.getTimestamp()));
		this.add(timestamp, detailGBC);
		
		//LABEL SENDER
		labelGBC.gridy = 4;
		JLabel senderLabel = new JLabel("Creator:");
		this.add(senderLabel, labelGBC);
		
		//SENDER
		detailGBC.gridy = 4;
		JTextField sender = new JTextField(arbitraryTransaction.getCreator().getAddress());
		sender.setEditable(false);
		this.add(sender, detailGBC);
		
		//LABEL SERVICE
		labelGBC.gridy = 5;
		JLabel serviceLabel = new JLabel("Service ID:");
		this.add(serviceLabel, labelGBC);
		
		//SERVICE
		detailGBC.gridy = 5;
		JTextField service = new JTextField(String.valueOf(arbitraryTransaction.getService()));
		service.setEditable(false);
		this.add(service, detailGBC);			
		
		//LABEL FEE
		labelGBC.gridy = 6;
		JLabel feeLabel = new JLabel("Fee:");
		this.add(feeLabel, labelGBC);
						
		//FEE
		detailGBC.gridy = 6;
		JTextField fee = new JTextField(arbitraryTransaction.getFee().toPlainString());
		fee.setEditable(false);
		this.add(fee, detailGBC);	
		
		//LABEL CONFIRMATIONS
		labelGBC.gridy = 7;
		JLabel confirmationsLabel = new JLabel("Confirmations:");
		this.add(confirmationsLabel, labelGBC);
								
		//CONFIRMATIONS
		detailGBC.gridy = 7;
		JLabel confirmations = new JLabel(String.valueOf(arbitraryTransaction.getConfirmations()));
		this.add(confirmations, detailGBC);	
		           
        //PACK
		this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
}
