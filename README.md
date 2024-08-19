# Exp6
Object Oriented Design Patterns and Code Refactoring in Java

# فاز دوم

## Facade

## Replace condition with polymorphism

برای این امر ابتدا یک کلاس پدر با ابسترکت متود toString تعریف می‌کنیم. سپس برای هر تایپ آدرس جداگانه یک کلاس که از این کلاس پدر ارث‌بری می‌کنند، نوشته و برای هرکدام متود toString را جداگانه پیاده سازی می‌کنیم.

## Separate query from modifier

در کلاس ErrorHandler، در متود printError، هم استیت فیلد hasError عوض می‌شد و هم لاگ ارور چاپ می‌شد. به این کلاس چند تابع جدید اضافه شد. اکنون فیلد hasError از طریق تابع hasError قابل دسترسی است و می‌توان به آن کوئری زد. همچنین این فیلد پرایوت شده است تا از کلاس‌های دیگر، قابل دسترسی نباشد.


# سوالات

سوال اول: 
-  الگو‌های خلاقانه: در این الگو به فرآیند ایجاد ابجکت‌ها و نمونه‌ها می‌پردازد و هدف آن مدیریت و کنترل فرآیند ساخت ابجکت‌ها به روشی انعطاف‌پذیر می‌باشد. یکی از این الگو‌ها Singleton است ک مطمئن می‌شود کخ تنها یک اینستنس از یک کلاس وجود دارد.
- الگو‌های ساختاری: تمرکز این دسته برروی ترکیب ابجکت‌ها و کلاس‌ها برای تشکیل ساختار‌های بزرگ‌نر و پیچیده‌تر می باشد. الگو‌های Adapter و Composite در این دسته قرار می گیرند و به دولوپر‌ها امکان مدیریت بهینه روابط بین ابجکت‌ها را می‌دهد.
- الگو‌های رفتاری: ایم الگو‌ها به تعاملات بین ابجکت‌ها و کلاس‌ها می‌پردازد. نحوه ارتباط بین ابجکت ها را تعریف می‌کنند تا وظایف به طور م.ثر تقسیم شوند. الگو Strategy از این دسته می‌باشد.

