package com.hnlg.enzyme.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码
 */
public class VerifyCodeUtil {

	private static final int width = 90;// 定义图片的width
	private static final int height = 38;// 定义图片的height
	private static final int codeCount = 4;// 定义图片上显示验证码的个数
	private static final int xx = 16;
	private static final int fontHeight = 28;
	private static final int codeY = 28;
	private static final char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static String execute(HttpServletResponse response) {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Arial", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);
		// 画边框。
		gd.setColor(Color.WHITE);
		gd.drawRect(0, 0, width - 1, height - 1);
		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuilder randomCode = new StringBuilder();
		int red, green, blue;
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(150);
			green = random.nextInt(150);
			blue = random.nextInt(150);
			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * xx, codeY);
			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		// 禁止图像缓存。
		response.setHeader("Content-type", "charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			// 将图像输出到Servlet输出流中。
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(buffImg, "jpeg", sos);
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randomCode.toString();
	}
}