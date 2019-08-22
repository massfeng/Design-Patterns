package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO 组合模式
 * 采用文件系统模拟组合模式
 * @Author xtf
 * @Date 2019/8/19 10:58
 */
public class FileSystem {
    public static void main(String[] args){
        IFile root = new Folder("我的电脑");
        root.createNewFile("C盘");
        root.createNewFile("D盘");
        root.createNewFile("E盘");
        IFile D = root.getIFile(1);
        D.createNewFile("project");
        D.createNewFile("电影");
        IFile project = D.getIFile(0);
        project.createNewFile("test1.java");
        project.createNewFile("test2.java");
        project.createNewFile("test3.java");
        IFile movie = D.getIFile(1);
        movie.createNewFile("致青春.avi");
        movie.createNewFile("速度与激情6.avi");

        /* 以上为当前文件系统的情况，下面我们尝试删除文件和文件夹 */
        display(null, root);
        System.out.println();

        project.delete();
        movie.getIFile(0).delete();

        System.out.println();
        display(null, root);
    }

    //打印文件系统
    public static void display(String prefix,IFile iFile){
        if (prefix == null) {
            prefix = "";
        }
        System.out.println(prefix + iFile.getName());
        if(iFile instanceof Folder){
            for (int i = 0; ; i++) {
                try {
                    if (iFile.getIFile(i) != null) {
                        display(prefix + "--", iFile.getIFile(i));
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }
    }
}

/**
 * @Description TODO
 * 文件和文件夹所要实现的接口
 * @Author xtf
 * @Date 2019/8/19 11:09
 */
interface IFile {
    /**
     * 删除整个文件
     **/
    void delete();
    /**
     * 获取文件名
     **/
    String getName();
    
    // 下面的三个方法是对于文件夹来说的，如果是文件则不可以使用这三个方法，将会抛出异常
    /**
     * 创建新文件
     **/
    void createNewFile(String name);
    /**
     * 删除文件夹下的某个文件
     **/
    void deleteFile(String name);
    /**
     * 获取文件夹下的某个文件
     **/
    IFile getIFile(int index);
}

/**
 * @Description TODO 文件夹
 * @Author xtf
 * @Date 2019/8/19 11:13
 */
class Folder implements IFile {
    /**
     * 文件夹名
     **/
    private String name;
    /**
     * 父文件夹的引用
     **/
    private IFile folder;
    /**
     * 文件夹下的文件(夹)列表
     **/
    private List<IFile> files;

    public Folder(String name) {
        this(name, null);
    }

    public Folder(String name, IFile folder) {
        super();
        this.name = name;
        this.folder = folder;
        files = new ArrayList<IFile>();
    }

    @Override
    /**
     * @Description TODO 先删除下面的文件夹然后删除自己
     * @Author xtf
     * @Date 2019/8/19 11:41
     * @Param []
     * @return void
     */
    public void delete() {
        List<IFile> copy = new ArrayList<>(this.files);
        System.out.println("-----------删除子文件-----------");
        for (IFile file :
                copy) {
            file.delete();
        }
        System.out.println("-----------删除子文件结束-----------");
        if(folder  != null) {
            folder.deleteFile(this.name);
        }
        System.out.println("---删除 [" + name +"] ---");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createNewFile(String name) {
        // 如果添加的文件名中有"."，说明是文件，添加一个文件
        // 否则是文件夹，添加一个文件夹
        if(name.contains(".")) {
            files.add(new File(name, this));
        } else {
            files.add(new Folder(name, this));
        }
    }

    @Override
    public void deleteFile(String name) {
        // 如果找到同名文件，则删除。
        for (IFile file:
             files) {
            if(file.getName().equals(name)) {
                files.remove(file);
            }
            break;
        }
    }

    @Override
    public IFile getIFile(int index) {
        return files.get(index);
    }
}

/**
 * @Description TODO 文件类
 * @Author xtf
 * @Date 2019/8/19 11:28
 */
class File implements IFile {
    /**
     * 文件名
     **/
    private String name;
    /**
     * 父文件夹
     **/
    private IFile folder;

    public File(String name, IFile folder) {
        super();
        this.name = name;
        this.folder = folder;
    }
    
    @Override
    public void delete() {
        folder.deleteFile(name);
        System.out.println("---删除 [" + name + "] ---");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createNewFile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteFile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IFile getIFile(int index) {
        throw new UnsupportedOperationException();
    }
}