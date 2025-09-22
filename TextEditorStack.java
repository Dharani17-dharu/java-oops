// Stack ADT Interface
public interface TextEditorStack<T> {
    void push(T item); // Adds an item to the top of the stack
    T pop();           // Removes and returns the item from the top of the stack
    T peek();          // Returns the item from the top of the stack without removing it
    boolean isEmpty(); // Checks if the stack is empty
    int size();        // Returns the number of items in the stack
}

// Array-based implementation of the Stack ADT
public class ArrayTextEditorStack<T> implements TextEditorStack<T> {
    private Object[] elements;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayTextEditorStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayTextEditorStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.elements = new Object[capacity];
        this.top = -1; // Indicates an empty stack
    }

    @Override
    public void push(T item) {
        if (top == elements.length - 1) {
            // Simple resizing for demonstration; in a real app, use more sophisticated resizing
            resize(elements.length * 2); 
        }
        elements[++top] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        T item = (T) elements[top];
        elements[top--] = null; // Dereference for garbage collection
        return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return (T) elements[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    private void resize(int newCapacity) {
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, top + 1);
        this.elements = newElements;
    }
}

// Example usage in a simulated Text Editor context
public class TextEditor {
    private TextEditorStack<String> undoStack;
    private TextEditorStack<String> redoStack;
    private String currentContent;

    public TextEditor(String initialContent) {
        this.undoStack = new ArrayTextEditorStack<>();
        this.redoStack = new ArrayTextEditorStack<>();
        this.currentContent = initialContent;
        System.out.println("Initial content: " + currentContent);
    }

    public void type(String text) {
        undoStack.push(currentContent); // Save current state for undo
        currentContent += text;
        redoStack = new ArrayTextEditorStack<>(); // Clear redo stack on new action
        System.out.println("Typed: \"" + text + "\", Current content: " + currentContent);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentContent); // Save current state for redo
            currentContent = undoStack.pop();
            System.out.println("Undo performed, Current content: " + currentContent);
        } else {
       
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentContent); // Save current state for undo
            currentContent = redoStack.pop();
            System.out.println("Redo performed, Current content: " + currentContent);
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor("Hello");
        editor.type(" World");
        editor.type("!");
        editor.undo();
        editor.redo();
        editor.type(" How are you?");
        editor.undo();
        editor.undo();
        editor.redo();
        editor.undo();
        editor.undo(); // Nothing to undo
        editor.redo(); // Nothing to redo
    }
}
