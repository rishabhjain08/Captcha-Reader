package Learner;

import javax.swing.table.AbstractTableModel;

public class TagIDModel extends AbstractTableModel {

        Object data[][]={
        };
 		String[] columnNames={"S.No.","Extracted","Respective Length","ID","Remember lengths","At Strech","Consider"};
                    static int COLUMN_COUNT=7;

	@Override
	public int getColumnCount() {
		return TagIDModel.COLUMN_COUNT;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
        @Override
            public Class getColumnClass(int column) {
                 return (data[0][column]).getClass();
        }

        @Override
              public String getColumnName(int column) {
                return columnNames[column];
              }

        @Override
              public boolean isCellEditable(int row, int column) {
                return (column == 5||column==3||column==4||column == 6);
              }

        @Override
              public void setValueAt(Object value, int row, int column) {
                data[row][column] = value;
                  fireTableRowsUpdated(row, row);
                }
        
        public boolean removeRow(int o){
            Object[][] newdata=new Object[data.length-1][];
            int i=0;
            int counter=0;
            boolean deleted=false;
            while(i<data.length){
                if(i!=o){
                    newdata[counter]=new Object[data[i].length];
                    newdata[counter] = data[i];
                    counter++;
                }
                else
                    deleted=true;
                i++;
            }
            if(deleted)
                data=newdata;
            return deleted;
        }
        public void addRow(Object[] o){
            Object[][] newdata=new Object[data.length+1][];
            int i=0;
            while(i<data.length){
                newdata[i]=new Object[data[i].length];
                newdata[i]=data[i];
                i++;
            }
            newdata[data.length]=o;
            data=newdata;
        }
}