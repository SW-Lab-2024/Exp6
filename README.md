# Exp6
Object Oriented Design Patterns and Code Refactoring in Java


### فاز اول
#### پیاده‌سازی الگوهای طراحی State و Strategy

#### مقدمه

در این پروژه، هدف پیاده‌سازی سیستمی برای اطلاع‌رسانی به شهروندان در مورد میزان فاصله و هزینه میان شهرهاست. این سیستم استانی شامل شهرهایی است که به وسیله مسیرهای مختلف به هم متصل شده‌اند. این مسیرها می‌توانند به صورت یک‌طرفه یا دوطرفه تنظیم شوند و دو روش تردد (قطار سریع‌السیر و اتوبوس) برای عبور و مرور میان شهرها در نظر گرفته شده است. برای مدیریت این پیچیدگی‌ها، از الگوهای طراحی **State** و **Strategy** بهره گرفته شده است.

---

#### الگوی طراحی State

الگوی طراحی State به ما اجازه می‌دهد که شیء بتواند رفتار خود را هنگامی که حالت داخلی‌اش تغییر می‌کند، تغییر دهد. در این پروژه، حالت‌های مختلف گراف (جهت‌دار و غیرجهت‌دار بودن) به عنوان حالت‌های مختلف سیستم پیاده‌سازی شده‌اند.

##### پیاده‌سازی:

1. **کلاس `DirectedState` و `NonDirectedState`:**
   - این دو کلاس نمایانگر حالت‌های مختلف گراف هستند. هر یک از این کلاس‌ها رفتار متفاوتی را برای گراف تعریف می‌کنند. برای مثال، در حالت `DirectedState`، یال‌ها به صورت جهت‌دار هستند، در حالی که در حالت `NonDirectedState`، یال‌ها به صورت غیرجهت‌دار تعریف می‌شوند.

2. **کلاس `Graph`:**
   - این کلاس نقش Context را ایفا می‌کند و با تغییر حالت خود (از طریق متدهایی مانند `setState`)، می‌تواند رفتار سیستم را تغییر دهد. کلاس Graph می‌تواند به سادگی بین حالت‌های `DirectedState` و `NonDirectedState` جابجا شود.

```java
public class Graph {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void addEdge(Node source, Node destination, int weight) {
        state.addEdge(source, destination, weight);
    }
}
```

---

#### الگوی طراحی Strategy

الگوی طراحی Strategy به ما اجازه می‌دهد که یک خانواده از الگوریتم‌ها را تعریف کنیم و هر کدام را به صورت جداگانه به عنوان یک استراتژی پیاده‌سازی کنیم. سپس، این استراتژی‌ها می‌توانند به صورت دینامیک در برنامه جایگزین یکدیگر شوند.

##### پیاده‌سازی:

1. **کلاس‌های `TrainStrategy` و `BusStrategy`:**
   - این دو کلاس، استراتژی‌های مختلف برای محاسبه زمان و هزینه سفر بین شهرها را پیاده‌سازی می‌کنند. در `TrainStrategy`، زمان سفر ثابت و معادل ۱ واحد زمانی در نظر گرفته شده است، در حالی که در `BusStrategy`، این زمان می‌تواند متغیر باشد.

2. **کلاس `DistanceStrategy`:**
   - این کلاس به عنوان یک اینترفیس یا کلاس والد برای استراتژی‌های مختلف عمل می‌کند و متدهایی مانند `calculateDistance` و `calculateCost` را تعریف می‌کند که در هر یک از استراتژی‌ها به صورت متفاوت پیاده‌سازی می‌شوند.

```java
public interface DistanceStrategy {
    int calculateDistance(Edge edge);
    int calculateCost(Edge edge);
}

public class TrainStrategy implements DistanceStrategy {
    @Override
    public int calculateDistance(Edge edge) {
        return 1; // زمان ثابت برای قطار سریع‌السیر
    }

    @Override
    public int calculateCost(Edge edge) {
        return edge.getWeight(); // هزینه بر اساس وزن یال
    }
}

public class BusStrategy implements DistanceStrategy {
    @Override
    public int calculateDistance(Edge edge) {
        return edge.getWeight(); // زمان بر اساس وزن یال
    }

    @Override
    public int calculateCost(Edge edge) {
        return edge.getWeight() * 2; // هزینه دو برابر وزن یال
    }
}
```

3. **کلاس `Edge`:**
   - این کلاس نمایانگر یک یال بین دو گره است که شامل اطلاعات مربوط به وزن یال (که می‌تواند نشان‌دهنده هزینه یا زمان باشد) است. کلاس‌های استراتژی از این اطلاعات برای محاسبه زمان و هزینه استفاده می‌کنند.

```java
public class Edge {
    private Node source;
    private Node destination;
    private int weight;

    public int getWeight() {
        return weight;
    }
}
```

---

#### جمع بندی

در این پروژه، با استفاده از الگوی طراحی **State** توانستیم حالت‌های مختلف سیستم (جهت‌دار و غیرجهت‌دار بودن گراف) را مدیریت کنیم و با بهره‌گیری از الگوی طراحی **Strategy** روش‌های مختلف محاسبه زمان و هزینه سفر را به صورت استراتژی‌های قابل تغییر پیاده‌سازی کنیم. این طراحی به ما امکان می‌دهد تا بدون تغییرات عمده در ساختار کد، رفتار سیستم را تغییر داده و استراتژی‌های مختلف را به راحتی اعمال کنیم.

# فاز دوم

## Facade
- کلاس ParserFacade یک اینترفیس ساده را برای کلاینت‌ها فراهمخ می‌کند و وابستگی و پیچیدگی پارسر را پنهان می‌کند. اگر پیاد‌ه‌سازی parser عوض شود، کلاینت نیازی به عوض کردن facade ندارد. همچنین Main را برای استفاده کردن از این Facade اپدیت می‌کنیم.
- همچنین برای کلاس Token،‌یک Facade تعریف کرده‌ایم تا برای کلاینت‌ها ارتباط با عملکرد‌های مربوط به توکن تسهیل شود. این Facade، لاجیک ایجاد توکن و مشخص کردن تایپ آن را encapsulate می‌کند. همچنین lexicalAnalyzer برای استفاده از این Facade اپدیت شده است.

## Replace condition with polymorphism

برای این امر ابتدا یک کلاس پدر با ابسترکت متود toString تعریف می‌کنیم. سپس برای هر تایپ آدرس جداگانه یک کلاس که از این کلاس پدر ارث‌بری می‌کنند، نوشته و برای هرکدام متود toString را جداگانه پیاده سازی می‌کنیم.

## Separate query from modifier

در کلاس ErrorHandler، در متود printError، هم استیت فیلد hasError عوض می‌شد و هم لاگ ارور چاپ می‌شد. به این کلاس چند تابع جدید اضافه شد. اکنون فیلد hasError از طریق تابع hasError قابل دسترسی است و می‌توان به آن کوئری زد. همچنین این فیلد پرایوت شده است تا از کلاس‌های دیگر، قابل دسترسی نباشد. همچنین تابع setError، برای عوض کردن استیت hasError نوشته شده است.

در رابطه با بازآرایی  Self Encapsulate Field میبینیم که در اینجا فیلدهای خصوصی ای را داشتیم که دسترسی آنها به صورت مستقیم بود و ما با افزودن توابع مربوطه ی getter و setter دسترسی درست به فیلد را ایجاد نمودیم.پس علاوه بر افزودن توابع،برای دسترسی به خود فیلد (جهت ایجاد امنیت برای یک فیلد خصوصی) هم باید از متود getter آن ها استفاده نماییم.

![image](https://github.com/user-attachments/assets/129cae40-9e00-409e-a215-4731e0bc8c79)

در ادامه هم پلاگین های مربوط به ورژن کامپایلر و encoding را افزودیم:
![image](https://github.com/user-attachments/assets/d1428295-b42e-4512-9f1c-f3b86d393961)

## Refactoring: Introduce Null Object
برای این بازآرایی باتوجه به حجم زیاد operation و یا operand های null تصمیم بر این گرفتیم که برای پیشگیری از اختلال کارکرد احتمالی پروژه یک کلاس null و همچنین یک enum null معرفی کنیم. null operand با توجه به اینکه از جنس آدرس بوده و در کل تنها ما یک شی null از آدرس خواهیم داشت(چرا که تفاوت خاصی در شی های null و موحودیت آنها نیست) پس از ارث بری کلاس null از کلاس address و افزودن فیلد isnull به تمام کلاس های فرزند address، برای nullAddress یک کانستراکتور singleton ساختیم.از آنجا به بعد در هر جای کد برای آدرسی که مقدار آن معادل null خواهد بود همان تنها شی از nullAddress را قرار دادیم.مزیت اینکار این بود که حتی برای مقایسه های مربوط به نال بودن یک آدرس هم میتوانستیم از دستور equals(nullAddress) استفاده کنیم.
![image](https://github.com/user-attachments/assets/b707e20f-e491-4b98-b0db-3af63cedea5e)

## Refactoring: Simplifying Conditionals
استفاده از Guard Clause در شرط‌های مربوط به Goto:

شرط مربوط به Goto به عنوان یک Guard Clause اضافه شد تا در صورت برآورده شدن این شرط، به سادگی به تکرار بعدی لوپ برویم. این باعث می‌شود که کد اصلی ساده‌تر و خواناتر شود

if (cols[i].startsWith("Goto")) {
    String temp = cols[i].substring(5);
    try {
        nonTerminals.put(i, NonTerminal.valueOf(temp));
    } catch (Exception e) {
        ErrorHandler.printError(e.getMessage());
    }
    continue;  // استفاده از Guard Clause برای ادامه دادن به تکرار بعدی
}
terminals.put(i, new Token(Token.getTyepFormString(cols[i]), cols[i]));


ساده‌سازی شرط‌های مربوط به acc و terminals:شرط‌های مربوط به acc، terminals و nonTerminals به صورت Guard Clause پیاده‌سازی شدند. این کار باعث شد که جریان اصلی کد ساده‌تر و بدون شرط‌های تو در تو باشد. همچنین، اگر هیچ‌کدام از این شرایط برقرار نباشد، یک Exception پرتاب می‌شود تا از وقوع شرایط غیرمنتظره جلوگیری شود.

if (cols[j].equals("acc")) {
    actionTable.get(actionTable.size() - 1).put(terminals.get(j), new Action(act.accept, 0));
    continue; 
}

if (terminals.containsKey(j)) {
    Token t = terminals.get(j);
    Action a = new Action(cols[j].charAt(0) == 'r' ? act.reduce : act.shift, Integer.parseInt(cols[j].substring(1)));
    actionTable.get(actionTable.size() - 1).put(t, a);
    continue; 
}

if (nonTerminals.containsKey(j)) {
    gotoTable.get(gotoTable.size() - 1).put(nonTerminals.get(j), Integer.parseInt(cols[j]));
    continue;  
}

throw new Exception("Unexpected condition encountered in ParseTable");


# سوالات

# سوال ۱ 
-  الگو‌های خلق (Creational): در این الگو به فرآیند ایجاد ابجکت‌ها و نمونه‌ها می‌پردازد و هدف آن مدیریت و کنترل فرآیند ساخت ابجکت‌ها به روشی انعطاف‌پذیر می‌باشد. یکی از این الگو‌ها Singleton است ک مطمئن می‌شود کخ تنها یک اینستنس از یک کلاس وجود دارد.
- الگو‌های ساختاری (Structural): تمرکز این دسته برروی ترکیب ابجکت‌ها و کلاس‌ها برای تشکیل ساختار‌های بزرگ‌نر و پیچیده‌تر می باشد. الگو‌های Adapter و Composite در این دسته قرار می گیرند و به دولوپر‌ها امکان مدیریت بهینه روابط بین ابجکت‌ها را می‌دهد.
- الگو‌های رفتاری (Behavioral): ایم الگو‌ها به تعاملات بین ابجکت‌ها و کلاس‌ها می‌پردازد. نحوه ارتباط بین ابجکت ها را تعریف می‌کنند تا وظایف به طور م.ثر تقسیم شوند. الگو Strategy از این دسته می‌باشد.
# سوال ۲
در فاز اول آزمایش، دو الگوی طراحی State و Strategy استفاده شده‌اند. این دو الگو جزو دسته الگوهای رفتاری (Behavioral Patterns) قرار می‌گیرند.
State Pattern: این الگو برای مدیریت حالت‌های مختلف یک شیء استفاده می‌شود، که در اینجا برای مدیریت حالت‌های جهت‌دار و غیرجهت‌دار گراف به کار رفته است.
Strategy Pattern: این الگو برای انتخاب الگوریتم‌های مختلف در زمان اجرا استفاده می‌شود. در این پروژه، از این الگو برای تعیین نحوه محاسبه هزینه و زمان سفر بین شهرها با استفاده از استراتژی‌های مختلف (قطار سریع‌السیر و اتوبوس) استفاده شده است.


# سوال ۳

علت انتخاب:
الگوی State به ما این امکان را می‌دهد که به راحتی رفتار سیستم را بر اساس حالت فعلی تغییر دهیم، بدون اینکه نیاز به تغییرات عمده در کد داشته باشیم. در اینجا، حالت گراف می‌تواند به صورت یک‌طرفه یا دوطرفه باشد و با استفاده از این الگو می‌توانیم به راحتی رفتار گراف را بر اساس حالت فعلی تنظیم کنیم. به این ترتیب، کاربران می‌توانند درخواست‌های خود را بر اساس حالت فعلی گراف (یک‌طرفه یا دوطرفه) ارسال کنند و سیستم به طور خودکار پاسخ مناسب را بر اساس حالت گراف بدهد.

نحوه تحقق الگو:
تعریف کلاس‌های State:

دو کلاس DirectedState و NonDirectedState به عنوان حالت‌های مختلف گراف تعریف شده‌اند. هر یک از این کلاس‌ها متدهای مخصوص به خود را برای مدیریت یال‌ها و ارتباطات گراف پیاده‌سازی می‌کنند.
تعریف Context:

کلاس Graph به عنوان Context عمل می‌کند که می‌تواند بین حالت‌های مختلف جابجا شود. این کلاس یک شیء از نوع State دارد که نشان‌دهنده حالت فعلی گراف است.
جابجایی بین حالات:

با استفاده از متد setState در کلاس Graph، می‌توان به راحتی حالت فعلی گراف را تغییر داد. مثلاً، اگر گراف در حالت یک‌طرفه باشد و بخواهیم آن را به دوطرفه تغییر دهیم، کافیست setState(new NonDirectedState()) را فراخوانی کنیم.
پاسخ به درخواست‌های کاربران:

هنگامی که کاربر درخواستی ارسال می‌کند، متدهای کلاس Graph مانند addEdge بر اساس حالت فعلی گراف رفتار مناسب را اجرا می‌کنند. اگر گراف در حالت یک‌طرفه باشد، یال‌ها به صورت جهت‌دار اضافه می‌شوند و اگر در حالت دوطرفه باشد، یال‌ها به صورت غیرجهت‌دار اضافه می‌شوند.
با استفاده از الگوی State، سیستم قادر است به صورت دینامیک و بر اساس حالت فعلی به درخواست‌های کاربران پاسخ دهد، بدون اینکه نیاز به تغییرات زیادی در ساختار کلی سیستم باشد.

# سوال ۴
در وهله ی اول مهم تر از همه این الگو  (Single Responsibility Principle SRP) را نقض میکند چون الگوی Singleton وظیفه مدیریت ایجاد و دسترسی به تنها یک نمونه از کلاس را به دوش می‌کشد. این ترکیب وظایف ممکن است باعث نقض SRP شود زیرا کلاس علاوه بر وظیفه اصلی خود، مسئولیت مدیریت چرخه حیات خود و هرگونه responsibility دیگر را نیز بر عهده خواهد داشت.


این طراحی اصل باز/بسته (Open/Closed Principle) را نقض می‌کند، زیرا کلاس Singleton خودش مسئول ایجاد نمونه‌اش است، در حالی که مصرف‌کنندگان معمولاً به طور سخت به نمونه واقعی آن وابسته می‌شوند. این وابستگی باعث می‌شود که تغییر پیاده‌سازی بدون انجام تغییرات گسترده در سراسر برنامه ممکن نباشد.

این طراحی همچنین اصل وارونگی وابستگی (Dependency Inversion Principle یا DIP) را نقض می‌کند، زیرا مصرف‌کنندگان همیشه مستقیماً به کلاس واقعی برای دریافت نمونه وابسته خواهند بود، در حالی که DIP توصیه می‌کند که از انتزاع‌ها استفاده شود. این امر باعث می‌شود که پیاده‌سازی Singleton به صورت غیرقابل جدایی همراه برنامه باقی بماند و از اضافه کردن مسائل فرعی، مانند تزئین‌کننده‌ها یا توزیع مشتری بدون پیاده‌سازی Singleton، جلوگیری می‌کند.

در این طراحی همچنین (Liskov Substitution Principle LSP) محقق نمی‌شود، دروافع الگوی Singleton اغلب به ارث‌بری نیاز ندارد و در صورت ارث‌بری، می‌تواند مشکل‌ساز شود زیرا نمونه جدید نمی‌تواند جایگزین نمونه Singleton شود، که این موضوع باعث نقض LSP می‌شود.

اصل Interface Segregation Principle (ISP) نقض نمیشود چون ارتباط چندانی با این مدل ندارد زیرا این اصل مرتبط با تفکیک وظایف در اینترفیس‌ها است، که در مورد Singleton اصولاً مشکلی ایجاد نمی‌کند.


# سوال ۵
clean code: اگر کد یک سری ویژگی که در ادامه به آنها اشاره میکنیم داشته باشد به آن کد تمیز میگوییم.اول از همه این مورد به خوانایی کد اشاره میکند علی الخصوص برای دیگر برنامه نویس ها.مثلا باید حواسمان به نام گذاری متغیر ها و شلوغ نبودن کلاس ها و توابع باشد.

هدف از مورد دوم ایچاد سادگی در تغییر کد است لذا باید از تکراری بودن بخش های مختلف کد حتی الامکان پرهیز شود که موقع تغییر تنها بخواهیم یک بخش از کد را تغییر دهیم که بدیهی است در غیراینصورت شاهد کندی فرایند خواهیم بود.
هدف از مورد سوم هم راحتی در نگه داری از کد است.ما برای تحقق این امر،باید کد هرچه کمتر و تمیزتری داشته باشیم.
در راستای تحقق مورد چهارم هم باید بدانیم که کد ما اهداف کدنظرمان را محقق میکند پس با رعایت تمیزی پاس شدن تست هایمان را به دنبال خواهیم داشت.
پس کلا هدف از کد تمیز صرفه جویی در زمان است که از طریق تمیزی کد و پیشگیری از سردرگمی برنامه نویس و بهینه سازی هرگونه عملیات مدنظر است.بهینه سازی برای ما به معنی هم داشتن بهترین عملکرد بوده و هم پیشگیری از انجام کار های اضافه و وجود بخش های اضافه و تکراری در کد.

Technincal Debit:  چبدهی فنی به این معناست که شنا فرض کنید یک خرید خاصی مدنظرتان است که هرچه سریعتر میخواهید به آن برسید.هرچند از عهده ی شمار خارج است پس به سراغ وام با سود میروید.بدیهتا در نهایت این شما هستید که در بلندمدت ضرر میکنید چون برای تسریع در یک فرایند شما مچبور به تهیه وام شدید و این موضوع در لحظه برای شما سوداور همین مسئله راجع به  بدهی فنی صدق میکند. فرض کنید تحت شرایط فشار کسب کار مجبور شدید که کدی را سریع به کارفرمای خو تحویل بدهید که هیچ گونه از اصول کد تمیز در آن رعایت نشده و اصلا برنامه تست ندارد.شاید در آن موعد شما کارتان را تحویل داده باشید اما در طولانی مدت هزینه ی زیاد مالی و زمانی بر تیم تحمیل خواهد شد.در طی این شرایط ما ممکن است به بدهی فنی بر بخوریم:فشار کسب کار،عدم آشنایی با اهمیت بازآرایی،نبود تست و مستند سازی،بازآرایی دیرهنگام،توسعه ی طولانی مدت کد در برنچ های مختلف(به وضوح پس از مدت طولانی به تناقضات بسیار در کد برمیخوریم،پس هرچقد کدنویسی ما ازیوله تر باشد،ریسک بدهی فنی بیشتر است) و همچنین نبود تعامل بین اعضای تیم به علت مشابه برای ما بدهی فنی خواهد ساخت.


code smells: بوهای کد نشانگر مشکلاتی هستند که می توان در طی بازسازی مجدد آنها را برطرف کرد. بوی کد به راحتی قابل تشخیص و رفع است، اما دایم هم میتواند علائم یک مشکل عمیق تر باشد.
# سوال ۶

بوهای کد نشانگر مشکلاتی هستند که می توان در طی بازسازی مجدد آنها را برطرف کرد. بوی کد به راحتی قابل تشخیص و رفع است، اما هی ممکن است فقط علائم یک مشکل عمیق تر باشد با کد


بادکننده ها: Bloaters کدها، روش‌ها و کلاس‌هایی هستند که به بسیار بزرگ شدند و کار کردن با آنها دشوار است. معمولاً این بوها فوراً ظاهر نمی‌شوند، بلکه در طول زمان ظی تکامل برنامه روی هم انباشته می‌شوند (علی الخصوص وقتی کسی تلاشی برای از بین بردن آنها نمیکند).مثال:
روش طولانی،کلاس بزرگ،وسواس اولیه،لیست پارامترهای طولانی،مجموعه داده ها

استفاده ناصحیح از اصول شی گرا: همه این بوها کاربرد ناقص یا نادرست اصول برنامه نویسی شی گرا هستند.برای مثال:
کلاس های جایگزین با واسط های مختلف،رد کردن وصیت ها،و بند های switch،فیلد موقت

مانع ساز های تغییر:

این بوها در شرایطی ایجاد می شوند که شما نیاز به تغییر چیزی در یک مکان در کد خود دارید، باید در جاهای دیگر نیز تغییرات زیادی ایجاد کنید. در نتیجه توسعه برنامه بسیار پیچیده تر و گران تر می شود. مثال ها:

تغییر واگرا،سلسله مراتب وراثت موازی،جراحی تفنگ ساچمه ای

بی مصرف ها!:

یک چیز بی‌معنی و غیر ضروری است که نبود آن کد را پاک‌تر، کارآمدتر و درک آسان‌تر می‌کند.رویکردها:
نظرات،کد تکراری،کلاس داده،کد مرده،کلاس تنبل،عمومیت گمانه زنی

جفت کننده ها:

تمام بوهای موجود در این گروه به جفت شدن بیش از حد بین کلاس ها اطلاق می شود یا نشان می دهد که اگر جفت با تفویض بیش از حد جایگزین شود چه اتفاقی می افتد.مثال:
حسادت به فیچر ها!صمیمیت نامناسب،کلاس کتابخانه ناقص،زنجیره پیام،مرد وسطی
# سوال ۷
کلاس تنبل در دسته ی بی مصرف ها(Dispensables) قرار میگیرد.

برای برطرف کردن آن درمولفه های تقریبا بی استفاده از بازارایی کلاس خطی (اگر یک کلاس نقش خاص و مسئولیت مهمی را در پروژه به عهده ندارد تمام فیچر ها و ویژگی های آن را به یک کلاس دیگر منتقل میکنیم) استفاده شده و همچنین برای رفع آن برای زیرکلاسی که توابع محدودی را دارد از روش فروپاشی سلسله مراتب(اگر زیرکلاس عملا مانند کلاس مرتبه بالاتر خود باشد باید زیر کلاس را با کلاس بالایی ادغام کنیم) میتوان استفاده کرد.

زمانی میتوان آن را در نظر نگرفت که این کلاس های تنبل برای مشخص سازی اهداف برای توسعه های آتی ایجاد شده اند.در این شرایط باید به دنبال ایجاد تعادل بین سادگی و وضوح کد باشیم.

# سوال ۸ 
- در کلاس ClassInfo، مقدار magic value  وجود دارد و نمی‌توان دلیل اسکیپ کردن ۳ کاراکتر ابتدایی توکن در خط ۵۷ را فهمید.
- عدم وحود کامنت کافی 
- در کلاس Phase2CodeFileManipulator یک سوییچ کیس بزرگ و پیچیده وجود دارد.
- همچنین این کلاس Phase2CodeFileManipulator بسیاز بزرگ است و باید به تکه‌های کوچکتر تقسیم شود.
- در کلاس LexicalAnalyzer متود بسیار طولانی وجود دارد.
- عدم encapsulate کردن فیلد‌هایی که فقط در کلاس استفاده می شوند.
- در سراسر پروژه ارور‌ها با دقت و به اندازه کافی هندل نشده‌اند.
- در کلاس MethodOverLoader، مقدار hard-code شده وجود دارد. (حروف الفیا)
- به اندازه کافی یونیت تست نوشته نشده است.
- وجود dead code در بعضی از کلاس‌ها.
- در کلاس Phase1CodeGenerator، کد تکراری برای ساخت فایل و دایرکتوری وجود دارد.
- نامگذاری متود isSuccessFull اشتباه است و باید isSuccessful باشد.
 # سوال ۹
پلاگین Formetter مطابق مستند ارائه شده از خود سایت آن یک موجو پلاگین Maven برای قالب‌بندی کد منبع جاوا با استفاده از فرمت‌کننده کد Eclipse.برای مثال پارامترهای Mojo با مشخص کردن فایل پیکربندی XML، انتهای خطوط، نسخه کامپایلر و مکان کد منبع، قالب‌بندی را سفارشی سازی می‌کنند. از قالب‌بندی مجدد فایل‌های منبع با استفاده از هش sha512 محتوا، در مقایسه با هش اصلی به هش پس از قالب‌بندی و هش حافظه پنهان، اجتناب می‌شود.
اینجا چند نمونه از پارمترهای داده شده مشاهده میکنیم.

همچنین مثالی از قابلیت های مشخص کردن پارامترهای دلخواه در پیکربندی XML مشاهده مینماییم:
ورژن کامپایلر:
<configuration>
    <compilerSource>1.9</compilerSource>
    <compilerCompliance>1.9</compilerCompliance>
    <compilerTargetPlatform>1.9</compilerTargetPlatform>
  </configuration>
فرمت encoding:
<plugin>
  <groupId>net.revelc.code.formatter</groupId>
  <artifactId>formatter-maven-plugin</artifactId>
  <version>2.24.2-SNAPSHOT</version>
  <configuration>
    <encoding>UTF-8</encoding>
  </configuration>
</plugin>

## ارتباط این پلاگین با Refactoring
افزودن پارامتز  های مختلف این پلاگین مانند این است که ما یک دستور کارو مشی کلی در کدنویسی مان داریم که در کل پروژه ناگزیر رعایت می شود.نوشتن یک مشی کلی از اضافه نویسی پرهیز کرده و به ما به روش بهتری کد تمیز را میدهد.برای مثال خیلی صریح و مستقیم با پارامتر complier version یا ecnoding پارامتر مربوطه را مشخص میکنند و اینگونه هر برنامه نویسی میتواند از یک فرمت پروژه استفاده کند و از ناهماهنگی و مشکلات ران شدن از این قبیل به سادگی میتوان پیشگیری کرد و مطمئنیم به ارور های ازین دست(ناهماهنگی مشارکت کننده ها باهم) نمیخوریم.
و همچنین به سادگی میتوان با پارامتر های <include > و<exclude> بخش هایی که از formatting استفاده میکنند را مشخص نمود.




