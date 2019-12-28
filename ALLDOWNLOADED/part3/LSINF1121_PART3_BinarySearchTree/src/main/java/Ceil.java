
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
		//TODO by student
		if (root==null){
			return null;
		}

		int rootval = root.getValue();
		if ( rootval == value){
			return value;
		}

		Node left= root.getLeft();
		if ( rootval < value ){ //si c'est plus grand à droite on doit d'office y aller
			return ceil(root.getRight(), value);
		}
		else if ( left!= null ){
			Object a = ceil(left,value);
			if ( a==null ) // si on a été trop loin à gauche en allant à gauche vive la récurcivité et sa "mémoire"
				return rootval;
			else{
				return (int) a;
			}
		}
		else if ( left==null && value < rootval){ //si on ne sait pas aller plus à gauche et que value est plus petit, on a trouvé
			return rootval;
		}
		return null;
    }
}
