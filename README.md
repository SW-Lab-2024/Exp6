# Exp6
Object Oriented Design Patterns and Code Refactoring in Java

# فاز دوم

## Facade

## Replace condition with polymorphism

برای این امر ابتدا یک کلاس پدر با ابسترکت متود toString تعریف می‌کنیم. سپس برای هر تایپ آدرس جداگانه یک کلاس که از این کلاس پدر ارث‌بری می‌کنند، نوشته و برای هرکدام متود toString را جداگانه پیاده سازی می‌کنیم.

## Separate query from modifier

در کلاس ErrorHandler، در متود printError، هم استیت فیلد hasError عوض می‌شد و هم لاگ ارور چاپ می‌شد. به این کلاس چند تابع جدید اضافه شد. اکنون فیلد hasError از طریق تابع hasError قابل دسترسی است و می‌توان به آن کوئری زد. همچنین این فیلد پرایوت شده است تا از کلاس‌های دیگر، قابل دسترسی نباشد.