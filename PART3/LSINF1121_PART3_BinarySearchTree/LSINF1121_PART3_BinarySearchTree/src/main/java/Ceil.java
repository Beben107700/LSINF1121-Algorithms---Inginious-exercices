
public class Ceil {
	/**
	 * Find in the tree the smallest element greater than or equal to value
	 * (so either the element itself or the element located directly after it
	 * in order of magnitude). If such an element does not exist,
	 * it must return null.
	 *
	 * Inserez votre reponse ici
	 */
	public static Integer ceil(Node root, int value) {
		while(True){
			if(root.getValue() == value){return new Integer(value);}
			if(root.getValue() < value){
				if(root.getRight() != null){
					root = root.getRight();
				}
				return null;
			}
			if(root.getValue() > value){
				if(root.getLeft == null){
					return root.getValue();
				}
				if(root.getLeft().getValue() < value){
					root = root.getLeft();
				}
				else {
					root = root.getLeft();
				}

			}
		}
		return null;
    }
}
